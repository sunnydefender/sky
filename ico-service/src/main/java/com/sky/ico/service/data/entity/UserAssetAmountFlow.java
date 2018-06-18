package com.sky.ico.service.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserAssetAmountFlow {
    private Long flowId;

    private Long assetId;

    private Long userId;

    private Integer flowMode;

    private Long coinId;

    private String coinName;

    private String coinToken;

    private Integer changeMode;

    private Integer inOrOut;

    private Integer amountMode;

    private BigDecimal changeAmount;

    private Long srcMainId;

    private Long srcSlaveId;

    private BigDecimal totalAmount;

    private BigDecimal afterTotalAmount;

    private BigDecimal balanceAmount;

    private BigDecimal afterBalanceAmount;

    private BigDecimal frozenAmount;

    private BigDecimal afterFrozenAmount;

    private BigDecimal cashFrozenAmount;

    private BigDecimal afterCashFrozenAmount;

    private BigDecimal buyFrozenAmount;

    private BigDecimal afterBuyFrozenAmount;

    private BigDecimal sellFrozenAmount;

    private BigDecimal afterSellFrozenAmount;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

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

    public Integer getFlowMode() {
        return flowMode;
    }

    public void setFlowMode(Integer flowMode) {
        this.flowMode = flowMode;
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

    public Integer getChangeMode() {
        return changeMode;
    }

    public void setChangeMode(Integer changeMode) {
        this.changeMode = changeMode;
    }

    public Integer getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Integer inOrOut) {
        this.inOrOut = inOrOut;
    }

    public Integer getAmountMode() {
        return amountMode;
    }

    public void setAmountMode(Integer amountMode) {
        this.amountMode = amountMode;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Long getSrcMainId() {
        return srcMainId;
    }

    public void setSrcMainId(Long srcMainId) {
        this.srcMainId = srcMainId;
    }

    public Long getSrcSlaveId() {
        return srcSlaveId;
    }

    public void setSrcSlaveId(Long srcSlaveId) {
        this.srcSlaveId = srcSlaveId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAfterTotalAmount() {
        return afterTotalAmount;
    }

    public void setAfterTotalAmount(BigDecimal afterTotalAmount) {
        this.afterTotalAmount = afterTotalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getAfterBalanceAmount() {
        return afterBalanceAmount;
    }

    public void setAfterBalanceAmount(BigDecimal afterBalanceAmount) {
        this.afterBalanceAmount = afterBalanceAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getAfterFrozenAmount() {
        return afterFrozenAmount;
    }

    public void setAfterFrozenAmount(BigDecimal afterFrozenAmount) {
        this.afterFrozenAmount = afterFrozenAmount;
    }

    public BigDecimal getCashFrozenAmount() {
        return cashFrozenAmount;
    }

    public void setCashFrozenAmount(BigDecimal cashFrozenAmount) {
        this.cashFrozenAmount = cashFrozenAmount;
    }

    public BigDecimal getAfterCashFrozenAmount() {
        return afterCashFrozenAmount;
    }

    public void setAfterCashFrozenAmount(BigDecimal afterCashFrozenAmount) {
        this.afterCashFrozenAmount = afterCashFrozenAmount;
    }

    public BigDecimal getBuyFrozenAmount() {
        return buyFrozenAmount;
    }

    public void setBuyFrozenAmount(BigDecimal buyFrozenAmount) {
        this.buyFrozenAmount = buyFrozenAmount;
    }

    public BigDecimal getAfterBuyFrozenAmount() {
        return afterBuyFrozenAmount;
    }

    public void setAfterBuyFrozenAmount(BigDecimal afterBuyFrozenAmount) {
        this.afterBuyFrozenAmount = afterBuyFrozenAmount;
    }

    public BigDecimal getSellFrozenAmount() {
        return sellFrozenAmount;
    }

    public void setSellFrozenAmount(BigDecimal sellFrozenAmount) {
        this.sellFrozenAmount = sellFrozenAmount;
    }

    public BigDecimal getAfterSellFrozenAmount() {
        return afterSellFrozenAmount;
    }

    public void setAfterSellFrozenAmount(BigDecimal afterSellFrozenAmount) {
        this.afterSellFrozenAmount = afterSellFrozenAmount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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