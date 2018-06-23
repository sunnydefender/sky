package com.sky.ico.service.service;

import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.common.id.IdUtils;
import com.sky.framework.redis.RedisData;
import com.sky.framework.task.Task;
import com.sky.framework.task.TaskManager;
import com.sky.framework.task.enums.TaskMode;
import com.sky.ico.service.common.EmailExecuteContext;
import com.sky.ico.service.constant.RedisConfigKey;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.data.entity.PlatformEmailSend;
import com.sky.ico.service.data.entity.builder.PlatformEmailSendBuilder;
import com.sky.ico.service.dto.EmailVerificationCodeParamDTO;
import com.sky.ico.service.enums.BusinessMode;
import com.sky.ico.service.errorcode.CommonErrorCode;
import com.sky.ico.service.pojo.EmailContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailVerificationCodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVerificationCodeService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisData redisData;

    @Autowired
    private MysqlIndependentService mysqlIndependentService;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    private static final String EMAIL_VERIFICATION_CODE_REDIS_KEY = "verification:code:email:";

    public void verify(String code, BusinessMode businessMode, String businessId) {
        String fieldId = buildFieldId(businessMode, businessId);
        String correctCode = redisTemplate.opsForValue().get(fieldId);
        if (null == correctCode || !correctCode.equalsIgnoreCase(code)) {
            throw new BusinessException(CommonErrorCode.VerificationCode.CODE_ERROR);
        }

        redisTemplate.delete(fieldId);
    }

    public void sendVerificationCode(EmailVerificationCodeParamDTO paramDTO) {
        // TODO: 频率控制
        String pinCode = String.format("%06d", random.nextInt(1000000));
        String fieldId = buildFieldId(paramDTO.getBusinessMode(), paramDTO.getBusinessId());

        long timeout = getTimeout(paramDTO.getBusinessMode());
        redisTemplate.opsForValue().set(fieldId, pinCode, timeout,TimeUnit.MINUTES);

        PlatformEmail platformEmail = emailService.getPlatformEmail(paramDTO.getBusinessMode());
        long sendId = IdUtils.getInstance().createFlowId();
        EmailContent emailContent = BusinessMode.build(paramDTO.getBusinessMode());
        emailContent.setContent(emailContent.getContent().replace("{code}", pinCode));
        Date current = new Date();
        PlatformEmailSend platformEmailSend = PlatformEmailSendBuilder.build(platformEmail, sendId, paramDTO.getBusinessId(), emailContent.getSubject(), emailContent.getContent(), current);
        mysqlIndependentService.insertPlatformEmailSend(platformEmailSend);

        Task task = Task.build(EmailExecuteContext.buildEmailHandler("EmailSendHandler", platformEmail.getEmailGroup(), platformEmail.getUsername()),
                String.valueOf(platformEmailSend.getSendId()), 3, 10000);
        task.setTaskMode(TaskMode.SPECIAL);
        taskManager.pushTask(task);
    }

    private String buildFieldId(BusinessMode businessMode, String businessId) {
        return EMAIL_VERIFICATION_CODE_REDIS_KEY + businessMode.name() + ":" + businessId;
    }

    private long getTimeout(BusinessMode businessMode) {
        String field = null;
        switch (businessMode) {
        case REGISTER:
            field = RedisConfigKey.EmailVerificationCode.REGISTER.name();
            break;
        }

        if (null == field) {
            return 5;
        }
        return Long.parseLong(redisData.getBaseData(field, "5"));
    }
}
