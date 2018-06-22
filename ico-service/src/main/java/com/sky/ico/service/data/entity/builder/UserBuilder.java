package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.EmailRegisterApply;
import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.enums.*;

import java.util.Date;

public class UserBuilder {
    public static User build(EmailRegisterApply apply, Date activeTime) {
        User entity = new User();
        entity.setUserId(apply.getUserId());
        entity.setUserMode(UserMode.NORMAL.getValue());
        entity.setAuthLevel(AuthLevel.NOT_AUTH.getValue());
        entity.setEmail(apply.getEmail());
        entity.setEmailStatus(UserEmailStatus.USED.getValue());
        entity.setEmailActiveTime(activeTime);
        entity.setMobile(null);
        entity.setMobileStatus(MobileStatus.NOT_USED.getValue());
        entity.setMobileActiveTime(null);
        entity.setUserStatus(UserStatus.USED.getValue());
        entity.setRegisterTime(apply.getRegisterTime());
        entity.setRegisterIp(apply.getRegisterIp());
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;
    }
}
