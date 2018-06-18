package com.sky.ico.service.data.entity.builder;

import com.sky.framework.common.exception.ErrorCode;
import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.dto.EmailRegisterParamDTO;
import com.sky.ico.service.enums.RegisterStatus;

import java.util.Date;

public class EmailRegisterApplyBuilder {
    public static EmailRegisterApply build(EmailRegisterParamDTO paramDTO) {
        EmailRegisterApply apply = new EmailRegisterApply();
        apply.setRequestRefNo(paramDTO.getRequestRefNo());
        apply.setApplyId(null);
        apply.setUserId(null);
        apply.setUserPwd(null);
        apply.setTradePwd(null);
        apply.setEmail(paramDTO.getEmail());
        apply.setEmailVerificationCode(paramDTO.getEmailVerificationCode());
        apply.setRegisterStatus(RegisterStatus.SUCCESS.getValue());
        apply.setResult(ErrorCode.SUCCESS);
        apply.setFailCode(null);
        apply.setFailReason(null);
        apply.setRegisterTime(null);
        apply.setRegisterIp(null);
        Date current = new Date();
        apply.setCreateTime(current);
        apply.setUpdateTime(current);
        return apply;
    }
}
