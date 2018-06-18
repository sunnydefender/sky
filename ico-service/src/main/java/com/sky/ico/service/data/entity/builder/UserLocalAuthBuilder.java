package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.data.entity.UserLocalAuth;

import java.util.Date;

public class UserLocalAuthBuilder {
    public static UserLocalAuth build(EmailRegisterApply apply) {
        UserLocalAuth entity = new UserLocalAuth();
        entity.setUserId(apply.getUserId());
        entity.setUserPwd(entity.getUserPwd());
        entity.setTradePwd(entity.getTradePwd());
        entity.setEmail(apply.getEmail());
        entity.setMobile(null);
        entity.setRegisterTime(apply.getRegisterTime());
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;
    }
}
