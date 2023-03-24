package com.rezalab.shopsmartly.service.master.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

public class FileWrapper extends BaseWrapper {
    private static final long serialVersionUID = -8239365434249207566L;

    private String name;
    private String extension;
    private String fileConverted;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getExtension() { return extension; }

    public void setExtension(String extension) { this.extension = extension; }

    public String getFileConverted() { return fileConverted; }

    public void setFileConverted(String fileConverted) { this.fileConverted = fileConverted; }
}
