package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.UserAssetAmountFlow;

public interface UserAssetAmountFlowMapper {
    int deleteByPrimaryKey(Long flowId);

    int insert(UserAssetAmountFlow record);

    int insertSelective(UserAssetAmountFlow record);

    UserAssetAmountFlow selectByPrimaryKey(Long flowId);

    int updateByPrimaryKeySelective(UserAssetAmountFlow record);

    int updateByPrimaryKey(UserAssetAmountFlow record);
}