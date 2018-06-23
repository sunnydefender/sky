package com.sky.ico.service.data.entity;

import java.util.Date;

public class PlatformEmailDailyStats {
    private Long statsId;

    private Long emailId;

    private String dayDate;

    private String username;

    private Integer daySuccessTimes;

    private Integer dayFailTimes;

    private Integer dayTimeoutTimes;

    private Integer dayFreezeTimes;

    private Date lastSuccessTime;

    private Date lastFailTime;

    private Integer lastSendStatus;

    private Integer continuousFailureTimes;

    private Integer last1minFailureTimes;

    private Date createTime;

    private Date updateTime;

    public Long getStatsId() {
        return statsId;
    }

    public void setStatsId(Long statsId) {
        this.statsId = statsId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate == null ? null : dayDate.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getDaySuccessTimes() {
        return daySuccessTimes;
    }

    public void setDaySuccessTimes(Integer daySuccessTimes) {
        this.daySuccessTimes = daySuccessTimes;
    }

    public Integer getDayFailTimes() {
        return dayFailTimes;
    }

    public void setDayFailTimes(Integer dayFailTimes) {
        this.dayFailTimes = dayFailTimes;
    }

    public Integer getDayTimeoutTimes() {
        return dayTimeoutTimes;
    }

    public void setDayTimeoutTimes(Integer dayTimeoutTimes) {
        this.dayTimeoutTimes = dayTimeoutTimes;
    }

    public Integer getDayFreezeTimes() {
        return dayFreezeTimes;
    }

    public void setDayFreezeTimes(Integer dayFreezeTimes) {
        this.dayFreezeTimes = dayFreezeTimes;
    }

    public Date getLastSuccessTime() {
        return lastSuccessTime;
    }

    public void setLastSuccessTime(Date lastSuccessTime) {
        this.lastSuccessTime = lastSuccessTime;
    }

    public Date getLastFailTime() {
        return lastFailTime;
    }

    public void setLastFailTime(Date lastFailTime) {
        this.lastFailTime = lastFailTime;
    }

    public Integer getLastSendStatus() {
        return lastSendStatus;
    }

    public void setLastSendStatus(Integer lastSendStatus) {
        this.lastSendStatus = lastSendStatus;
    }

    public Integer getContinuousFailureTimes() {
        return continuousFailureTimes;
    }

    public void setContinuousFailureTimes(Integer continuousFailureTimes) {
        this.continuousFailureTimes = continuousFailureTimes;
    }

    public Integer getLast1minFailureTimes() {
        return last1minFailureTimes;
    }

    public void setLast1minFailureTimes(Integer last1minFailureTimes) {
        this.last1minFailureTimes = last1minFailureTimes;
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