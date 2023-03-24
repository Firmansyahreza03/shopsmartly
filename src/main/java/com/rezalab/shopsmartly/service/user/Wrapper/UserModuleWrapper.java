package com.rezalab.shopsmartly.service.user.Wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class UserModuleWrapper extends BaseWrapper {
    private static final long serialVersionUID = -4549519466584063773L;

    private Long userAccountId;
    private String email;
    private Long userInfoId;
    private Long userProfileId;

    public Long getUserAccountId() { return userAccountId;}

    public void setUserAccountId(Long userAccountId) { this.userAccountId = userAccountId;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Long getUserInfoId() {return userInfoId;}

    public void setUserInfoId(Long userInfoId) {this.userInfoId = userInfoId;}

    public Long getUserProfileId() {return userProfileId;}

    public void setUserProfileId(Long userProfileId) {this.userProfileId = userProfileId;}
}
