package com.rezalab.shopsmartly.model.master;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class Category extends BaseModel {
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_code")
    private String code;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}
}
