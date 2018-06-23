package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.data.entity.PlatformEmailDailyStats;
import com.sky.ico.service.enums.EmailSendStatus;

import java.util.Date;

/**
 * Created by easyfun on 2018/6/23.
 */
public class PlatformEmailDailyStatsBuilder {
    public static PlatformEmailDailyStats build(PlatformEmail platformEmail, long statsId, String dayDate) {
        PlatformEmailDailyStats entity = new PlatformEmailDailyStats();
        entity.setStatsId(statsId);
        entity.setEmailId(platformEmail.getEmailId());
        entity.setDayDate(dayDate);
        entity.setUsername(platformEmail.getUsername());
        entity.setDaySuccessTimes(0);
        entity.setDayFailTimes(0);
        entity.setDayTimeoutTimes(0);
        entity.setDayFreezeTimes(0);
        entity.setLastSuccessTime(null);
        entity.setLastFailTime(null);
        entity.setContinuousFailureTimes(0);
        entity.setLast1minFailureTimes(0);
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        entity.setLastSendStatus(EmailSendStatus.SUCCESS.getValue());
        return entity;
    }
}
