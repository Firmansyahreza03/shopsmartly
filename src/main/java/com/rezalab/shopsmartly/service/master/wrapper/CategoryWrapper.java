package com.rezalab.shopsmartly.service.master.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class CategoryWrapper extends BaseWrapper {

    private static final long serialVersionUID = 4114200107129200929L;

    private String name;
    private String code;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }
}
