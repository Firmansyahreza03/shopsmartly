package com.rezalab.shopsmartly.service.master.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class CompanyWrapper extends BaseWrapper {

    private static final long serialVersionUID = 2232374260087996156L;

    private String name;
    private String address;
    private String secondAddress;
    private String zip;
    private String phoneNumber;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSecondAddress() { return secondAddress; }

    public void setSecondAddress(String secondAddress) { this.secondAddress = secondAddress; }

    public String getZip() { return zip; }

    public void setZip(String zip) { this.zip = zip; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
