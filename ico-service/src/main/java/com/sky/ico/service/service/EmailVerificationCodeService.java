package com.sky.ico.service.service;

import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.redis.RedisData;
import com.sky.ico.service.constant.RedisConfigKey;
import com.sky.ico.service.dto.EmailVerificationCodeParamDTO;
import com.sky.ico.service.enums.BusinessMode;
import com.sky.ico.service.errorcode.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailVerificationCodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVerificationCodeService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisData redisData;

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

    public void sendVerificationCode(HttpServletRequest request, EmailVerificationCodeParamDTO paramDTO) {
        // TODO: 频率控制
        String pinCode = String.format("%06d", random.nextInt(1000000));
        String fieldId = buildFieldId(paramDTO.getBusinessMode(), paramDTO.getBusinessId());

        long timeout = getTimeout(paramDTO.getBusinessMode());
        redisTemplate.opsForValue().set(fieldId, pinCode, timeout,TimeUnit.MINUTES);
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
