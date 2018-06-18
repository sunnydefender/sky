package com.sky.ico.service.data.dao;

import com.sky.ico.service.data.entity.UserAsset;

import java.util.List;

public interface UserAssetMapper {
    int deleteByPrimaryKey(Long assetId);

    int insert(UserAsset record);

    int insertList(List<UserAsset> record);

    int insertSelective(UserAsset record);

    UserAsset selectByPrimaryKey(Long assetId);

    int updateByPrimaryKeySelective(UserAsset record);

    int updateByPrimaryKey(UserAsset record);
}