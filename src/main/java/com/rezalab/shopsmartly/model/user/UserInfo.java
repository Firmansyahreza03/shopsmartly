package com.rezalab.shopsmartly.model.user;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
public class UserInfo extends BaseModel {
    @Column(name = "join_date")
    private LocalDate joinDate;
    @Column(name = "last_login")
    private LocalDate lastLogin;
    @Column(name = "is_new_password")
    private Boolean isNewPassword;
    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;
    @Column(name = "session_id", unique = true)
    private String sessionId;
    @Column(name = "trial_login_attempt")
    private Integer trialLoginAttempt;

}
