package com.sky.ico.service.service;

import com.sky.framework.common.exception.BusinessException;
import com.sky.ico.service.enums.BusinessMode;
import com.sky.ico.service.errorcode.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationCodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVerificationCodeService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String EMAIL_VERIFICATION_CODE_REDIS_KEY = "verification.code.email";

    public void verify(String code, BusinessMode businessMode, String businessId) {
        String fieldId = buildFieldId(businessMode, businessId);
        String correctCode = (String) redisTemplate.opsForHash().get(EMAIL_VERIFICATION_CODE_REDIS_KEY, fieldId);
        if (null == correctCode || correctCode.equalsIgnoreCase(code)) {
            throw new BusinessException(CommonErrorCode.VerificationCode.CODE_ERROR);
        }
    }

    private String buildFieldId(BusinessMode businessMode, String businessId) {
        return businessMode.name() + ":" + businessId;
    }
}
