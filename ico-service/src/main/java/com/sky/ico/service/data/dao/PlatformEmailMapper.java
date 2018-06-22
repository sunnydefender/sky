package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.PlatformEmail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PlatformEmailMapper {
    int deleteByPrimaryKey(Long emailId);

    int insert(PlatformEmail record);

    int insertSelective(PlatformEmail record);

    PlatformEmail selectByPrimaryKey(Long emailId);

    List<PlatformEmail> selectAvailableListByEmailGroup(String emailGroup);

    List<PlatformEmail> selectAllListByEmailGroup(String emailGroup);

    int updateByPrimaryKeySelective(PlatformEmail record);

    int updateByPrimaryKey(PlatformEmail record);

    int updateTimesSelective(@Param("emailId") Long emailId,
                             @Param("successTimes") Integer successTimes,
                             @Param("failTimes") Integer failTimes,
                             @Param("timeoutTimes") Integer timeoutTimes,
                             @Param("freezeTimes") Integer freezeTimes,
                             @Param("updateTime") Date updateTime);

}