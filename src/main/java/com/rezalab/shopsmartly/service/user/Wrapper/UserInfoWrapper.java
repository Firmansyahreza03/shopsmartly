package com.rezalab.shopsmartly.service.user.Wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

import java.time.LocalDate;

public class UserInfoWrapper extends BaseWrapper {
    private static final long serialVersionUID = 62526221197967278L;

    private LocalDate joinDate;
    private LocalDate lastLogin;
    private Boolean isNewPassword;
    private Boolean isLoggedIn;
    private String sessionId;
    private Integer trialLoginAttempt;

    public LocalDate getJoinDate() { return joinDate; }

    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public LocalDate getLastLogin() { return lastLogin; }

    public void setLastLogin(LocalDate lastLogin) { this.lastLogin = lastLogin; }

    public Boolean getNewPassword() { return isNewPassword; }

    public void setNewPassword(Boolean newPassword) { isNewPassword = newPassword; }

    public Boolean getLoggedIn() { return isLoggedIn; }

    public void setLoggedIn(Boolean loggedIn) { isLoggedIn = loggedIn; }

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public Integer getTrialLoginAttempt() { return trialLoginAttempt; }

    public void setTrialLoginAttempt(Integer trialLoginAttempt) { this.trialLoginAttempt = trialLoginAttempt; }
}
