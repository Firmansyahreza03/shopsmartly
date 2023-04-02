package com.rezalab.shopsmartly.service.user.wrapper;

public class LoginWrapper {
    private String email;
    private String token;
    private String role;
    private Integer version;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public Integer getVersion() {return version;}

    public void setVersion(Integer version) {this.version = version;}
}
