package com.sky.ico.service.data.entity;

import java.util.Date;

public class UserRealAuth {
    private Long userId;

    private Integer authLevel;

    private Integer cardMode;

    private String cardNo;

    private Integer areaMode;

    private String areaName;

    private String realName;

    private String cardImageA;

    private String cardImageB;

    private String cardImageHandA;

    private String cardImageHandB;

    private Integer cardAuthStatu;

    private Date cardAuthTime;

    private Date createTime;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    public Integer getCardMode() {
        return cardMode;
    }

    public void setCardMode(Integer cardMode) {
        this.cardMode = cardMode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Integer getAreaMode() {
        return areaMode;
    }

    public void setAreaMode(Integer areaMode) {
        this.areaMode = areaMode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCardImageA() {
        return cardImageA;
    }

    public void setCardImageA(String cardImageA) {
        this.cardImageA = cardImageA == null ? null : cardImageA.trim();
    }

    public String getCardImageB() {
        return cardImageB;
    }

    public void setCardImageB(String cardImageB) {
        this.cardImageB = cardImageB == null ? null : cardImageB.trim();
    }

    public String getCardImageHandA() {
        return cardImageHandA;
    }

    public void setCardImageHandA(String cardImageHandA) {
        this.cardImageHandA = cardImageHandA == null ? null : cardImageHandA.trim();
    }

    public String getCardImageHandB() {
        return cardImageHandB;
    }

    public void setCardImageHandB(String cardImageHandB) {
        this.cardImageHandB = cardImageHandB == null ? null : cardImageHandB.trim();
    }

    public Integer getCardAuthStatu() {
        return cardAuthStatu;
    }

    public void setCardAuthStatu(Integer cardAuthStatu) {
        this.cardAuthStatu = cardAuthStatu;
    }

    public Date getCardAuthTime() {
        return cardAuthTime;
    }

    public void setCardAuthTime(Date cardAuthTime) {
        this.cardAuthTime = cardAuthTime;
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