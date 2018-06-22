package com.sky.ico.service.errorcode;

import com.sky.framework.common.exception.ErrorCode;

public class CommonErrorCode {
    public enum User implements ErrorCode {
        /** 用户已存在 */
        USER_EXISTED("00000000"),

        /** 用户不存在 */
        USER_NOT_EXISTED("00000001"),

        ;

        private String errorCode;

        private User(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorCode () {
            return errorCode;
        }

        public void setErrorCode (String errorCode){
            this.errorCode = errorCode;
        }

        @Override
        public String getFailCode () {
            return errorCode;
        }
    }


    public enum VerificationCode implements ErrorCode {
        /** 验证码错误 */
        CODE_ERROR("00001000"),
        ;

        private String errorCode;

        private VerificationCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorCode () {
            return errorCode;
        }

        public void setErrorCode (String errorCode){
            this.errorCode = errorCode;
        }

        @Override
        public String getFailCode () {
            return errorCode;
        }
    }

    public enum Email implements ErrorCode {
        /** 发送错误 */
        SEND_EMAIL_ERROR("00002000"),

        /** 根据BusinessMode获取平台邮箱错误 */
        PLATFORM_EMAL_GROUP_FROM_BUSINESS_MODE_ERROR("00002001"),

        /** 平台邮箱不可用 */
        PLATFORM_EMAL_GROUP_NOT_AVAILABLE("00002002")
        ;

        private String errorCode;

        private Email(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorCode () {
            return errorCode;
        }

        public void setErrorCode (String errorCode){
            this.errorCode = errorCode;
        }

        @Override
        public String getFailCode () {
            return errorCode;
        }
    }
}