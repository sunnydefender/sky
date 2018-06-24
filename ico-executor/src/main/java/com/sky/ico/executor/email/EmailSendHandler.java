package com.sky.ico.executor.email;

import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.common.exception.ErrorCode;
import com.sky.framework.common.id.IdUtils;
import com.sky.framework.common.utils.ExceptionUtil;
import com.sky.framework.common.utils.JsonUtil;
import com.sky.framework.common.utils.DateUtil;
import com.sky.framework.task.Task;
import com.sky.framework.task.TaskManager;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.enums.TaskMode;
import com.sky.framework.task.enums.TaskResult;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.framework.task.util.TaskRedisLock;
import com.sky.ico.service.common.EmailExecuteContext;
import com.sky.ico.service.common.utils.EmailUtil;
import com.sky.ico.service.data.dao.PlatformEmailDailyStatsMapper;
import com.sky.ico.service.data.dao.PlatformEmailMapper;
import com.sky.ico.service.data.dao.PlatformEmailSendMapper;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.data.entity.PlatformEmailDailyStats;
import com.sky.ico.service.data.entity.PlatformEmailSend;
import com.sky.ico.service.data.entity.builder.PlatformEmailDailyStatsBuilder;
import com.sky.ico.service.enums.EmailSendStatus;
import com.sky.ico.service.enums.PlatformEmailStatus;
import com.sky.ico.service.errorcode.CommonErrorCode;
import com.sky.ico.service.service.EmailService;
import org.apache.commons.mail.EmailException;
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
    
    @Value("${captcha.expire:300}")
    private int captchaExpire;

    @Value("${pinCode.expire:300}")
    private int pinCodeExpire;

    @Value("${pinCode.push.expire:300}")
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

    @Autowired
    private PlatformEmailDailyStatsMapper platformEmailDailyStatsMapper;

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
        PlatformEmail updatePlatformEmail = new PlatformEmail();
        updatePlatformEmail.setEmailId(platformEmail.getEmailId());
        PlatformEmailDailyStats platformEmailDailyStats = selectPlatformEmailDailyStats(platformEmail);
        TaskExecuteResult result = new TaskExecuteResult();

        if (platformEmail.getEmailStatus() != PlatformEmailStatus.AVAILABLE.getValue()) {
            LOGGER.info("邮箱暂时不可用.platformEmail={}", JsonUtil.toJSONString(platformEmail));

            moveEmailSendTask(taskPO, platformEmail);
            result.setTaskResult(TaskResult.MOVE);
            return result;
        }

        PlatformEmailSend platformEmailSend = platformEmailSendMapper.selectByPrimaryKey(Long.valueOf(taskPO.getTaskKey()));
        PlatformEmailSend updatePlatformEmailSend = new PlatformEmailSend();
        updatePlatformEmailSend.setSendId(platformEmailSend.getSendId());

        Date current = new Date();
        Date timeout = DateUtil.addSeconds(platformEmailSend.getApplyTime(), pinCodeExpire);
        if (current.compareTo(timeout) >= 0) {
            LOGGER.debug("邮件发送超时. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));

            updatePlatformEmailSend.setSendStatus(EmailSendStatus.FAIL.getValue());
            updatePlatformEmailSend.setResult(ErrorCode.FAIL);
            updatePlatformEmailSend.setFailCode(CommonErrorCode.Email.SEND_TIMEOUT.getFailCode());
            updatePlatformEmailSend.setUpdateTime(current);
            platformEmailSendMapper.updateByPrimaryKeySelective(updatePlatformEmailSend);

            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), null, null, 1, null, current);
            result.setTaskResult(TaskResult.FAIL);

            platformEmailDailyStatsMapper.updateTimesSelective(platformEmailDailyStats.getStatsId(),
                    null, null, 1, null, null, null, null, null, current, null);
            return result;
        }

        try {
            EmailUtil.sendEmail(platformEmail, platformEmailSend.getToAddress(), platformEmailSend.getSubject(), platformEmailSend.getContent());
            LOGGER.info("注册邮件发送成功. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));
            updatePlatformEmailSend.setSendStatus(EmailSendStatus.SUCCESS.getValue());
            current = new Date();
            updatePlatformEmailSend.setUpdateTime(current);
            platformEmailSendMapper.updateByPrimaryKeySelective(updatePlatformEmailSend);

            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), 1, null, null, null, current);

            platformEmailDailyStatsMapper.updateTimesSelective(platformEmailDailyStats.getStatsId(),
                    1, null, null, null, current, null, null, null, current, ErrorCode.SUCCESS);
            platformEmailDailyStatsMapper.updateFailureTimes(platformEmailDailyStats.getStatsId(),
                    0, 0, current);

        }catch (EmailException e){
            LOGGER.error("发送邮件失败.paltformEmailSend={}", JsonUtil.toJSONString(platformEmailSend), e);
            updatePlatformEmailDailyStats(platformEmail, updatePlatformEmail, platformEmailDailyStats);

            Throwable cause = e;
            Throwable t = e.getCause();
            while (null != t) {
                cause = t;
                t = t.getCause();
            }
            updatePlatformEmailSend(updatePlatformEmailSend, CommonErrorCode.Email.SEND_EMAIL_ERROR.getFailCode(), ExceptionUtil.buildFailReason(cause));
            result.setTaskResult(TaskResult.FAIL);
            return result;
        } catch (Exception e) {
            LOGGER.error("发送邮件失败，稍后重试.paltformEmailSend={}", JsonUtil.toJSONString(platformEmailSend), e);

            updatePlatformEmailDailyStats(platformEmail, updatePlatformEmail, platformEmailDailyStats);

            if (taskPO.getRetriedTimes() < 3) {
                result.setTaskResult(TaskResult.RETRY);
                return result;
            }

            LOGGER.debug("注册邮件已超过投递次数. platformEmailSend={}", JsonUtil.toJSONString(platformEmailSend));
            updatePlatformEmailSend(updatePlatformEmailSend, CommonErrorCode.Email.OVER_MAX_RETRY_TIMES.getFailCode(), ExceptionUtil.buildFailReason(e));
            result.setTaskResult(TaskResult.FAIL);
            return result;
        }

        result.setTaskResult(TaskResult.SUCCESS);
        return result;
    }

    private void updatePlatformEmailSend(PlatformEmailSend updatePlatformEmailSend, String failCode, String failReason) {
        updatePlatformEmailSend.setSendStatus(EmailSendStatus.FAIL.getValue());
        updatePlatformEmailSend.setResult(ErrorCode.FAIL);
        updatePlatformEmailSend.setFailCode(failCode);
        updatePlatformEmailSend.setFailReason(failReason);
        Date current = new Date();
        updatePlatformEmailSend.setUpdateTime(current);
        platformEmailSendMapper.updateByPrimaryKeySelective(updatePlatformEmailSend);
    }

    private void updatePlatformEmailDailyStats(PlatformEmail platformEmail, PlatformEmail updatePlatformEmail, PlatformEmailDailyStats platformEmailDailyStats) {
        Date current = new Date();
        platformEmailDailyStats.setContinuousFailureTimes(platformEmailDailyStats.getContinuousFailureTimes() + 1);
        if (platformEmailDailyStats.getContinuousFailureTimes() > platformEmail.getFreezeContinuousFailureTimes()) {
            updatePlatformEmail.setEmailStatus(PlatformEmailStatus.FROZEN.getValue());
            updatePlatformEmail.setFreezeStartTime(current);
            updatePlatformEmail.setFreezeEndTime(DateUtil.addDays(DateUtil.addMinutes(current, 10), 1));
            updatePlatformEmail.setUpdateTime(current);
            platformEmailMapper.updateByPrimaryKeySelective(updatePlatformEmail);

            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), 0, 1, 0, 1, current);
            platformEmailDailyStatsMapper.updateTimesSelective(platformEmailDailyStats.getStatsId(),
                    null, 1, null, 1, null, current, 1, 1, current, ErrorCode.FAIL);
        } else {
            platformEmailMapper.updateTimesSelective(platformEmail.getEmailId(), 0, 1, 0, 0, current);
            platformEmailDailyStatsMapper.updateTimesSelective(platformEmailDailyStats.getStatsId(),
                    null, 1, null, null, null, current, 1, 1, current, ErrorCode.FAIL);
        }
    }

    private PlatformEmailDailyStats selectPlatformEmailDailyStats(PlatformEmail platformEmail) {
        Date current = new Date();
        String dayDate = DateUtil.formatDate(current, DateUtil.YYYYMMDD);
        PlatformEmailDailyStats stats = platformEmailDailyStatsMapper.selectByEmailIdAndDayDate(platformEmail.getEmailId(), dayDate);
        if (null == stats) {
            long statsId = IdUtils.getInstance().createPrimaryKeyId();
            stats = PlatformEmailDailyStatsBuilder.build(platformEmail, statsId, dayDate);
            platformEmailDailyStatsMapper.insert(stats);
        }
        return stats;
    }

    private boolean moveEmailSendTask(TaskPO taskPO, PlatformEmail platformEmail) {
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
