package com.rezalab.shopsmartly.service.transaction.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class StoreWrapper extends BaseWrapper {
    private static final long serialVersionUID = 476219094673077782L;

    private String name;
    private String code;
    private String address;
    private String secondAddress;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSecondAddress() { return secondAddress; }

    public void setSecondAddress(String secondAddress) { this.secondAddress = secondAddress; }
}
