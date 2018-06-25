package com.sky.ico.service.data.entity;

import java.util.Date;

public class PlatformEmail {
    private Long emailId;

    private String username;

    private String password;

    private String platformName;

    private String smtpHost;

    private Integer smtpPort;

    private Integer emailMode;

    private Integer emailFunction;

    private String emailGroup;

    private Integer emailStatus;

    private Integer sendIntervalSeconds;

    private Integer successTimes;

    private Integer failTimes;

    private Integer timeoutTimes;

    private Integer freezeContinuousFailureTimes;

    private Integer freezeLast1minFailureTimes;

    private Integer freezeTimes;

    private Date freezeStartTime;

    private Date freezeEndTime;

    private Date createTime;

    private Date updateTime;

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName == null ? null : platformName.trim();
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost == null ? null : smtpHost.trim();
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Integer getEmailMode() {
        return emailMode;
    }

    public void setEmailMode(Integer emailMode) {
        this.emailMode = emailMode;
    }

    public Integer getEmailFunction() {
        return emailFunction;
    }

    public void setEmailFunction(Integer emailFunction) {
        this.emailFunction = emailFunction;
    }

    public String getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup == null ? null : emailGroup.trim();
    }

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Integer getSendIntervalSeconds() {
        return sendIntervalSeconds;
    }

    public void setSendIntervalSeconds(Integer sendIntervalSeconds) {
        this.sendIntervalSeconds = sendIntervalSeconds;
    }

    public Integer getSuccessTimes() {
        return successTimes;
    }

    public void setSuccessTimes(Integer successTimes) {
        this.successTimes = successTimes;
    }

    public Integer getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(Integer failTimes) {
        this.failTimes = failTimes;
    }

    public Integer getTimeoutTimes() {
        return timeoutTimes;
    }

    public void setTimeoutTimes(Integer timeoutTimes) {
        this.timeoutTimes = timeoutTimes;
    }

    public Integer getFreezeContinuousFailureTimes() {
        return freezeContinuousFailureTimes;
    }

    public void setFreezeContinuousFailureTimes(Integer freezeContinuousFailureTimes) {
        this.freezeContinuousFailureTimes = freezeContinuousFailureTimes;
    }

    public Integer getFreezeLast1minFailureTimes() {
        return freezeLast1minFailureTimes;
    }

    public void setFreezeLast1minFailureTimes(Integer freezeLast1minFailureTimes) {
        this.freezeLast1minFailureTimes = freezeLast1minFailureTimes;
    }

    public Integer getFreezeTimes() {
        return freezeTimes;
    }

    public void setFreezeTimes(Integer freezeTimes) {
        this.freezeTimes = freezeTimes;
    }

    public Date getFreezeStartTime() {
        return freezeStartTime;
    }

    public void setFreezeStartTime(Date freezeStartTime) {
        this.freezeStartTime = freezeStartTime;
    }

    public Date getFreezeEndTime() {
        return freezeEndTime;
    }

    public void setFreezeEndTime(Date freezeEndTime) {
        this.freezeEndTime = freezeEndTime;
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