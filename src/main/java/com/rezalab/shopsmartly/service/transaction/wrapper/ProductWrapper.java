package com.rezalab.shopsmartly.service.transaction.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class ProductWrapper extends BaseWrapper {

    private static final long serialVersionUID = 1477581398733291804L;

    private String name;
    private String code;
    private String description;
    private String fileId;
    private String fileConverted;
    private String categoryId;
    private String categoryName;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getFileId() { return fileId; }

    public void setFileId(String fileId) { this.fileId = fileId; }

    public String getFileConverted() { return fileConverted; }

    public void setFileConverted(String fileConverted) { this.fileConverted = fileConverted; }

    public String getCategoryId() { return categoryId; }

    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
