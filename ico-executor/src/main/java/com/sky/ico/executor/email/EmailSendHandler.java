package com.sky.ico.executor.email;

import com.sky.framework.common.utils.JsonUtil;
import com.sky.framework.task.Task;
import com.sky.framework.task.TaskManager;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.enums.TaskMode;
import com.sky.framework.task.enums.TaskResult;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.framework.task.util.DateUtil;
import com.sky.framework.task.util.TaskRedisLock;
import com.sky.ico.service.common.EmailExecuteContext;
import com.sky.ico.service.data.dao.PlatformEmailMapper;
import com.sky.ico.service.data.dao.PlatformEmailSendMapper;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.data.entity.PlatformEmailSend;
import com.sky.ico.service.enums.EmailSendStatus;
import com.sky.ico.service.enums.PlatformEmailStatus;
import com.sky.ico.service.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by easyfun on 2018/6/4.
 */
@Component
public class EmailSendHandler implements ITaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSendHandler.class);
    private static final String LOCK_ID_EMAIL_SENDER = "email.sender";
    
    @Value("${captcha.expire}")
    private int captchaExpire;

    @Value("${pinCode.expire:300}")
    private int pinCodeExpire;

    @Value("${pinCode.push.expire}")
    private int pinCodePushExpire;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskRedisLock taskRedisLock;

    @Autowired
    private PlatformEmailMapper platformEmailMapper;

    @Autowired
    private PlatformEmailSendMapper platformEmailSendMapper;

    private Random random = new Random();

    @Override
    public TaskExecuteResult execute(TaskPO taskPO, Object params) {
        PlatformEmail local = (PlatformEmail)params;
        boolean lock = false;
        String lockId = LOCK_ID_EMAIL_SENDER + "." + local.getEmailGroup();
        try {
            lock = taskRedisLock.lockTimeout(lockId, 15);
            if (lock) {
                return doJob(taskPO, local);
            }

            TaskExecuteResult result = new TaskExecuteResult();
            result.setTaskResult(TaskResult.RETRY);
            return result;
        } finally {
            if (lock) {
                taskRedisLock.unlock(lockId);
            }
        }
    }

    public TaskExecuteResult doJob(TaskPO taskPO, PlatformEmail local) {
        PlatformEmail platformEmail = platformEmailMapper.selectByPrimaryKey(local.getEmailId());
        TaskExecuteResult result = new TaskExecuteResult();

        if (platformEmail.getEmailStatus() != PlatformEmailStatus.AVAILABLE.getValue()) {
            LOGGER.info("邮箱暂时不可用.platformEmail={}", JsonUtil.toJSONString(platformEmail));

            moveEmailSendTask(taskPO, platformEmail);
            result.setTaskResult(TaskResult.FAIL);
            return result;
        }

        PlatformEmailSend platformEmailSend = platformEmailSendMapper.selectByPrimaryKey(Long.valueOf(taskPO.getTaskKey()));
        PlatformEmailSend updatePlatformEmailSend = new PlatformEmailSend();
        updatePlatformEmailSend.setSendId(platformEmailSend.getSendId());

        Date current = new Date();
        Date timeout = DateUtil.addSeconds(platformEmailSend.getApplyTime(), pinCodeExpire);
        if (current.compareTo(timeout) >= 0) {
            LOGGER.debug("邮件发送超时. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));
            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), null, null, 1, null, current);
            result.setTaskResult(TaskResult.FAIL);
            return result;
        }

        try {
            emailService.sendEmail(platformEmail, platformEmailSend.getToAddress(), platformEmailSend.getSubject(), platformEmailSend.getContent());
            LOGGER.info("注册邮件发送成功. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));
            updatePlatformEmailSend.setSendStatus(EmailSendStatus.SUCCESS.getValue());
            current = new Date();
            updatePlatformEmailSend.setUpdateTime(current);
            platformEmailSendMapper.updateByPrimaryKeySelective(updatePlatformEmailSend);

            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), 1, 0, 0, 0, current);

        }catch (Exception e) {
            LOGGER.debug("发送注册邮件失败.", e);
            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), 0, 1, 0, 0, current);

            if (taskPO.getRetriedTimes() < 3) {
                result.setTaskResult(TaskResult.RETRY);
                return result;
            }

            LOGGER.debug("注册邮件已超过投递次数. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));

            updatePlatformEmailSend.setSendStatus(EmailSendStatus.FAIL.getValue());
            current = new Date();
            updatePlatformEmailSend.setUpdateTime(current);
            platformEmailSendMapper.updateByPrimaryKeySelective(updatePlatformEmailSend);

            result.setTaskResult(TaskResult.FAIL);
            return result;
        }

        result.setTaskResult(TaskResult.SUCCESS);
        return result;
    }

    public boolean moveEmailSendTask(TaskPO taskPO, PlatformEmail platformEmail) {
        List<PlatformEmail> availableList = platformEmailMapper.selectAvailableListByEmailGroup(platformEmail.getEmailGroup());

        if (null == availableList || availableList.size() == 0) {
            LOGGER.error("[邮箱监控告警]企业邮箱配置错误. platformEmail={}, emailPoolStr={}", JsonUtil.toJSONString(platformEmail), availableList);
            return false;
        }

        PlatformEmail toPlatformEmail = null;
        List<PlatformEmail> emailList = new ArrayList<>();
        for (PlatformEmail entry : availableList) {
            if (entry.getUsername().equals(platformEmail.getUsername())
                    && entry.getEmailGroup().equals(platformEmail.getEmailGroup())
                    && entry.getSmtpHost().equals(platformEmail.getSmtpHost())
                    && entry.getPassword().equals(platformEmail.getPassword())
                    && entry.getSmtpPort() == platformEmail.getSmtpPort()) {
                continue;
            }
            emailList.add(entry);
        }

        if (!CollectionUtils.isEmpty(emailList)) {
            int index = random.nextInt(10000) % emailList.size();
            toPlatformEmail = emailList.get(index);
        }
        if (null == toPlatformEmail) {
            LOGGER.error("[邮箱监控告警]所有邮箱冻结，不可用.platformEmail={}, emailList={}", JsonUtil.toJSONString(platformEmail), JsonUtil.toJSONString(emailList));
            return false;
        }

        String handler = EmailExecuteContext.buildEmailHandler(EmailSendHandler.class.getSimpleName(),
                toPlatformEmail.getEmailGroup(),
                toPlatformEmail.getUsername());
        Task task = Task.build(handler, taskPO.getTaskKey(), taskPO.getMaxRetryTimes(), taskPO.getRetryInterval());
        task.setParam(taskPO.getParam());
        task.setTaskMode(TaskMode.SPECIAL);
        return  taskManager.pushTask(task);
    }
}
