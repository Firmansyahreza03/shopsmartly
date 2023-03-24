package com.rezalab.shopsmartly.service.user.Wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class UserProfileWrapper extends BaseWrapper {
    private static final long serialVersionUID = -7908305758776444464L;

    private String name;
    private String address;
    private String secondAddress;
    private Long fileId;
    private String fileConverted;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getSecondAddress() {return secondAddress;}

    public void setSecondAddress(String secondAddress) {this.secondAddress = secondAddress;}

    public Long getFileId() {return fileId;}

    public void setFileId(Long fileId) {this.fileId = fileId;}

    public String getFileConverted() {return fileConverted;}

    public void setFileConverted(String fileConverted) {this.fileConverted = fileConverted;}
}
