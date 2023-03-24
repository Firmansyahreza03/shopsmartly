package com.rezalab.shopsmartly.model.transaction;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store extends BaseModel {
    @Column(name = "store_name")
    private String name;
    @Column(name = "store_code", unique = true)
    private String code;
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    @Column(name = "second_address", columnDefinition = "TEXT")
    private String secondAddress;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getSecondAddress() {return secondAddress;}

    public void setSecondAddress(String secondAddress) {this.secondAddress = secondAddress;}
}
