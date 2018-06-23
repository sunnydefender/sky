package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.PlatformEmailDailyStats;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PlatformEmailDailyStatsMapper {
    int deleteByPrimaryKey(Long statsId);

    int insert(PlatformEmailDailyStats record);

    int insertSelective(PlatformEmailDailyStats record);

    PlatformEmailDailyStats selectByPrimaryKey(Long statsId);

    PlatformEmailDailyStats selectByEmailIdAndDayDate(@Param("emailId") Long emailId, @Param("dayDate") String dayDate);

    int updateByPrimaryKeySelective(PlatformEmailDailyStats record);

    int updateByPrimaryKey(PlatformEmailDailyStats record);

    int updateTimesSelective(@Param("statsId") Long statsId,
                             @Param("daySuccessTimes") Integer daySuccessTimes,
                             @Param("dayFailTimes") Integer dayFailTimes,
                             @Param("dayTimeoutTimes") Integer dayTimeoutTimes,
                             @Param("dayFreezeTimes") Integer dayFreezeTimes,
                             @Param("lastSuccessTime") Date lastSuccessTime,
                             @Param("lastFailTime") Date lastFailTime,
                             @Param("continuousFailureTimes") Integer continuousFailureTimes,
                             @Param("last1minFailureTimes") Integer last1minFailureTimes,
                             @Param("updateTime") Date updateTime,
                             @Param("lastSendStatus") Integer lastSendStatus);

    int updateFailureTimes(@Param("statsId") Long statsId,
                             @Param("continuousFailureTimes") Integer continuousFailureTimes,
                             @Param("last1minFailureTimes") Integer last1minFailureTimes,
                             @Param("updateTime") Date updateTime);
}