package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.data.entity.User;

import java.util.Date;

public class UserBuilder {
    public static User build(EmailRegisterApply apply) {
        User entity = new User();
        entity.setUserId(apply.getUserId());
        entity.setUserMode(null);
        entity.setAuthLevel(null);
        entity.setEmail(apply.getEmail());
        entity.setEmailStatus(null);
        entity.setEmailActiveTime(null);
        entity.setMobile(null);
        entity.setMobileStatus(null);
        entity.setMobileActiveTime(null);
        entity.setUserStatus(null);
        entity.setRegisterTime(apply.getRegisterTime());
        entity.setRegisterIp(apply.getRegisterIp());
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;
    }
}
