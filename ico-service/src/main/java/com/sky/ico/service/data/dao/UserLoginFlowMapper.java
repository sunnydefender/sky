package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.UserLoginFlow;

public interface UserLoginFlowMapper {
    int deleteByPrimaryKey(Long loginId);

    int insert(UserLoginFlow record);

    int insertSelective(UserLoginFlow record);

    UserLoginFlow selectByPrimaryKey(Long loginId);

    int updateByPrimaryKeySelective(UserLoginFlow record);

    int updateByPrimaryKey(UserLoginFlow record);
}