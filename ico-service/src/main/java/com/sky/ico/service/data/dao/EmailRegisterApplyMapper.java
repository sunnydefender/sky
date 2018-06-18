package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.EmailRegisterApply;

public interface EmailRegisterApplyMapper {
    int deleteByPrimaryKey(Long applyId);

    int insert(EmailRegisterApply record);

    int insertSelective(EmailRegisterApply record);

    EmailRegisterApply selectByPrimaryKey(Long applyId);

    int updateByPrimaryKeySelective(EmailRegisterApply record);

    int updateByPrimaryKey(EmailRegisterApply record);
}