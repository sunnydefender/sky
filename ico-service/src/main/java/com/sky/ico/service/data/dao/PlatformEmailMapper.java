package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.PlatformEmail;

public interface PlatformEmailMapper {
    int deleteByPrimaryKey(Long emailId);

    int insert(PlatformEmail record);

    int insertSelective(PlatformEmail record);

    PlatformEmail selectByPrimaryKey(Long emailId);

    int updateByPrimaryKeySelective(PlatformEmail record);

    int updateByPrimaryKey(PlatformEmail record);
}