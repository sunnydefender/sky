package com.sky.ico.service.dto;

import com.sky.framework.common.dto.base.BaseParamDTO;
import org.hibernate.validator.constraints.NotBlank;

public class EmailRegisterParamDTO extends BaseParamDTO {
    @NotBlank
    private String userPwd;

    @NotBlank
    private String email;

    @NotBlank
    private String emailVerificationCode;

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }
}
