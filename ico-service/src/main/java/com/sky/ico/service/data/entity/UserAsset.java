package com.sky.ico.service.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserAsset {
    private Long assetId;

    private Long userId;

    private Long coinId;

    private String coinName;

    private String coinToken;

    private Integer isCash;

    private Integer isRecharge;

    private Integer isTrade;

    private BigDecimal totalAmount;

    private BigDecimal balanceAmount;

    private BigDecimal frozenAmount;

    private BigDecimal cashFrozenAmount;

    private BigDecimal buyFrozenAmount;

    private BigDecimal sellFrozenAmount;

    private Integer assetStatus;

    private Integer version;

    private String walletId;

    private String walletName;

    private Integer walletMode;

    private String walletAddress;

    private String ethAddressPwd;

    private String ethAddressIv;

    private String ethAddressPhrase;

    private String ethAddressFilePath;

    private Date createTime;

    private Date updateTime;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName == null ? null : coinName.trim();
    }

    public String getCoinToken() {
        return coinToken;
    }

    public void setCoinToken(String coinToken) {
        this.coinToken = coinToken == null ? null : coinToken.trim();
    }

    public Integer getIsCash() {
        return isCash;
    }

    public void setIsCash(Integer isCash) {
        this.isCash = isCash;
    }

    public Integer getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(Integer isRecharge) {
        this.isRecharge = isRecharge;
    }

    public Integer getIsTrade() {
        return isTrade;
    }

    public void setIsTrade(Integer isTrade) {
        this.isTrade = isTrade;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getCashFrozenAmount() {
        return cashFrozenAmount;
    }

    public void setCashFrozenAmount(BigDecimal cashFrozenAmount) {
        this.cashFrozenAmount = cashFrozenAmount;
    }

    public BigDecimal getBuyFrozenAmount() {
        return buyFrozenAmount;
    }

    public void setBuyFrozenAmount(BigDecimal buyFrozenAmount) {
        this.buyFrozenAmount = buyFrozenAmount;
    }

    public BigDecimal getSellFrozenAmount() {
        return sellFrozenAmount;
    }

    public void setSellFrozenAmount(BigDecimal sellFrozenAmount) {
        this.sellFrozenAmount = sellFrozenAmount;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId == null ? null : walletId.trim();
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName == null ? null : walletName.trim();
    }

    public Integer getWalletMode() {
        return walletMode;
    }

    public void setWalletMode(Integer walletMode) {
        this.walletMode = walletMode;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress == null ? null : walletAddress.trim();
    }

    public String getEthAddressPwd() {
        return ethAddressPwd;
    }

    public void setEthAddressPwd(String ethAddressPwd) {
        this.ethAddressPwd = ethAddressPwd == null ? null : ethAddressPwd.trim();
    }

    public String getEthAddressIv() {
        return ethAddressIv;
    }

    public void setEthAddressIv(String ethAddressIv) {
        this.ethAddressIv = ethAddressIv == null ? null : ethAddressIv.trim();
    }

    public String getEthAddressPhrase() {
        return ethAddressPhrase;
    }

    public void setEthAddressPhrase(String ethAddressPhrase) {
        this.ethAddressPhrase = ethAddressPhrase == null ? null : ethAddressPhrase.trim();
    }

    public String getEthAddressFilePath() {
        return ethAddressFilePath;
    }

    public void setEthAddressFilePath(String ethAddressFilePath) {
        this.ethAddressFilePath = ethAddressFilePath == null ? null : ethAddressFilePath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}