package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.PlatformEmailSend;

public interface PlatformEmailSendMapper {
    int deleteByPrimaryKey(Long sendId);

    int insert(PlatformEmailSend record);

    int insertSelective(PlatformEmailSend record);

    PlatformEmailSend selectByPrimaryKey(Long sendId);

    int updateByPrimaryKeySelective(PlatformEmailSend record);

    int updateByPrimaryKey(PlatformEmailSend record);
}