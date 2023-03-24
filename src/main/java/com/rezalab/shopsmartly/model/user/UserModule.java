package com.rezalab.shopsmartly.model.user;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_module")
public class UserModule extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    public UserAccount getUserAccount() {return userAccount;}

    public void setUserAccount(UserAccount userAccount) {this.userAccount = userAccount;}

    public UserInfo getUserInfo() {return userInfo;}

    public void setUserInfo(UserInfo userInfo) {this.userInfo = userInfo;}

    public UserProfile getUserProfile() {return userProfile;}

    public void setUserProfile(UserProfile userProfile) {this.userProfile = userProfile;}
}
