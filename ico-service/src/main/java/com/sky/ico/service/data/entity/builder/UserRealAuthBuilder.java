package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.data.entity.UserRealAuth;

import java.util.Date;

public class UserRealAuthBuilder {
    public static UserRealAuth build(User user) {
        UserRealAuth entity = new UserRealAuth();
        entity.setUserId(user.getUserId());
        entity.setAuthLevel(user.getAuthLevel());
        entity.setCardNo(null);
        entity.setAreaMode(null);
        entity.setAreaName(null);
        entity.setRealName(null);
        entity.setCardImageA(null);
        entity.setCardImageB(null);
        entity.setCardImageHandA(null);
        entity.setCardImageHandB(null);
        entity.setCardAuthStatu(null);
        entity.setCardAuthTime(null);
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;
    }
}
