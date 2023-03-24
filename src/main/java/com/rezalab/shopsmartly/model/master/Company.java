package com.rezalab.shopsmartly.model.master;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_company")
public class Company extends BaseModel {
    @Column(name = "company_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "second_address")
    private String secondAddress;
    @Column(name = "zip_code")
    private String zip;
    @Column(name = "phone_number")
    private String phoneNumber;

    public String getSecondAddress() {return secondAddress;}

    public void setSecondAddress(String secondAddress) {this.secondAddress = secondAddress;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getZip() {return zip;}

    public void setZip(String zip) {this.zip = zip;}
}
