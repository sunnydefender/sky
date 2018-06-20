package com.sky.ico.service.data.entity.builder;

import com.sky.framework.common.exception.ErrorCode;
import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.dto.EmailRegisterParamDTO;
import com.sky.ico.service.enums.RegisterStatus;

import java.util.Date;

public class EmailRegisterApplyBuilder {
    public static EmailRegisterApply build(EmailRegisterParamDTO paramDTO,
                                           long applyId,
                                           long userId,
                                           String userPwd,
                                           String tradePwd,
                                           Date registerTime,
                                           String registerIp) {
        EmailRegisterApply apply = new EmailRegisterApply();
        apply.setRequestRefNo(paramDTO.getRequestRefNo());
        apply.setApplyId(applyId);
        apply.setUserId(userId);
        apply.setUserPwd(userPwd);
        apply.setTradePwd(tradePwd);
        apply.setEmail(paramDTO.getEmail());
        apply.setEmailVerificationCode(paramDTO.getEmailVerificationCode());
        apply.setRegisterStatus(RegisterStatus.SUCCESS.getValue());
        apply.setResult(ErrorCode.SUCCESS);
        apply.setFailCode(null);
        apply.setFailReason(null);
        apply.setRegisterTime(registerTime);
        apply.setRegisterIp(registerIp);
        Date current = new Date();
        apply.setCreateTime(current);
        apply.setUpdateTime(current);
        return apply;
    }
}
