package com.rezalab.shopsmartly.model.master;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File extends BaseModel {
    @Column(name = "file_name")
    private String name;
    @Column(name = "extension")
    private String extension;
    @Column(name = "file_64")
    private String fileConverted;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getExtension() {return extension;}

    public void setExtension(String extension) {this.extension = extension;}

    public String getFileConverted() {return fileConverted;}

    public void setFileConverted(String fileConverted) {this.fileConverted = fileConverted;}
}
