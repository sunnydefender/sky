package com.sky.ico.service.enums;

import com.sky.framework.common.exception.BusinessException;
import com.sky.ico.service.errorcode.CommonErrorCode;

public enum PlatformEmailGroup {
    /** 注册 */
    REGISTER;

    public static String buildPlatformEmailGroup(BusinessMode businessMode) {
        switch (businessMode) {
            case REGISTER:
                return PlatformEmailGroup.REGISTER.name();
        }
        throw new BusinessException(CommonErrorCode.Email.FAIL_GET_PLATFORM_EMAL_BY_BUSINESS_MODE);
    }
}
