package com.rezalab.shopsmartly.model.master;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class Role extends BaseModel {
    @Column(name = "role_name")
    private String name;
    @Column(name = "role_code", unique = true, length = 4)
    private String code;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}
}
