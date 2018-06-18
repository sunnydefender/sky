package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.UserLocalAuth;

public interface UserLocalAuthMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserLocalAuth record);

    int insertSelective(UserLocalAuth record);

    UserLocalAuth selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserLocalAuth record);

    int updateByPrimaryKey(UserLocalAuth record);
}