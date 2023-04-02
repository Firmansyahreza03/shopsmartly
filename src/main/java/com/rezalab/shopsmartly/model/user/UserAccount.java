package com.rezalab.shopsmartly.model.user;

import com.rezalab.shopsmartly.model.base.BaseModel;
import com.rezalab.shopsmartly.model.master.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount extends BaseModel {
    @Column(name = "user_email", unique = true)
    private String email;
    @Column(name = "user_password", columnDefinition = "TEXT")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public Role getRole() {return role;}

    public void setRole(Role role) {this.role = role;}
}
