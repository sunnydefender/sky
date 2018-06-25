package com.sky.ico.service.common.utils;

import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.common.utils.JsonUtil;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.errorcode.CommonErrorCode;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/6/24.
 */
public class EmailUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    public static void sendEmail(PlatformEmail platformEmail, String toAddress, String subject, String content) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(platformEmail.getSmtpHost());
        email.setSmtpPort(platformEmail.getSmtpPort());
        email.setAuthenticator(new DefaultAuthenticator(platformEmail.getUsername(), platformEmail.getPassword()));
        email.setSSLOnConnect(true);
        email.setFrom(platformEmail.getUsername(), platformEmail.getPlatformName());
        email.setSubject(subject);
        email.setMsg(content);
        email.addTo(toAddress);
        email.send();
    }
}
