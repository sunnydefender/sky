package com.sky.ico.service.data.entity.builder;

import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.data.entity.UserAsset;
import com.sky.ico.service.enums.AssetStatus;

import java.math.BigDecimal;
import java.util.Date;

public class UserAssetBuilder {
    public static UserAsset build(User user, long assetId) {
        UserAsset entity = new UserAsset();
        entity.setAssetId(assetId);
        entity.setUserId(user.getUserId());
        entity.setCoinId(null);
        entity.setCoinName(null);
        entity.setCoinToken(null);
        entity.setIsCash(null);
        entity.setIsRecharge(null);
        entity.setIsTrade(null);
        entity.setTotalAmount(BigDecimal.ZERO);
        entity.setBalanceAmount(BigDecimal.ZERO);
        entity.setFrozenAmount(BigDecimal.ZERO);
        entity.setCashFrozenAmount(BigDecimal.ZERO);
        entity.setBuyFrozenAmount(BigDecimal.ZERO);
        entity.setSellFrozenAmount(BigDecimal.ZERO);
        entity.setAssetStatus(AssetStatus.USED.getValue());
        entity.setVersion(0);
        entity.setWalletId(null);
        entity.setWalletName(null);
        entity.setWalletMode(null);
        entity.setWalletAddress(null);
        entity.setEthAddressPwd(null);
        entity.setEthAddressIv(null);
        entity.setEthAddressPhrase(null);
        entity.setEthAddressFilePath(null);
        Date current = new Date();
        entity.setCreateTime(current);
        entity.setUpdateTime(current);
        return entity;

    }
}
