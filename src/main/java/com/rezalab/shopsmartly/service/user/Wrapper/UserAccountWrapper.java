package com.rezalab.shopsmartly.service.user.Wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class UserAccountWrapper extends BaseWrapper {
    private static final long serialVersionUID = -6799199860070121599L;

    private String email;
    private String password;
    private String roleCode;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRoleCode() { return roleCode; }

    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
}
