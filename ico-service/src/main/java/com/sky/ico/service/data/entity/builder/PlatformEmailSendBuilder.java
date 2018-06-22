package com.sky.ico.service.data.entity.builder;

import com.sky.framework.common.exception.ErrorCode;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.data.entity.PlatformEmailSend;
import com.sky.ico.service.enums.EmailSendStatus;
import com.sky.ico.service.errorcode.CommonErrorCode;

import java.util.Date;

/**
 * Created by easyfun on 2018/6/21.
 */
public class PlatformEmailSendBuilder {

    public static PlatformEmailSend build(PlatformEmail platformEmail,
                                          long sendId,
                                          String toAddress,
                                          String subject,
                                          String content,
                                          Date current) {
        PlatformEmailSend entity = new PlatformEmailSend();
        entity.setSendId(sendId);
        entity.setEmailId(platformEmail.getEmailId());
        entity.setUsername(platformEmail.getUsername());
        entity.setToAddress(toAddress);
        entity.setSubject(subject);
        entity.setContent(content);
        entity.setSendStatus(EmailSendStatus.ACCEPTED.getValue());
        entity.setResult(ErrorCode.ACCEPTED);
        entity.setFailCode(null);
        entity.setFailReason(null);
        entity.setApplyTime(current);
        entity.setFinishTime(null);
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;
    }
}
