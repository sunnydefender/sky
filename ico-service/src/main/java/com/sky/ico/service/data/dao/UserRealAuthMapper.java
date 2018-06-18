package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.UserRealAuth;

public interface UserRealAuthMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserRealAuth record);

    int insertSelective(UserRealAuth record);

    UserRealAuth selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserRealAuth record);

    int updateByPrimaryKey(UserRealAuth record);
}