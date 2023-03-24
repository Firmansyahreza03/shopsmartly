package com.rezalab.shopsmartly.service.master.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class RoleWrapper extends BaseWrapper {
    private static final long serialVersionUID = 1012848116420338441L;

    private String name;
    private String code;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }
}
