package com.rezalab.shopsmartly.model.transaction;

import com.rezalab.shopsmartly.model.base.BaseModel;
import com.rezalab.shopsmartly.model.master.Category;
import com.rezalab.shopsmartly.model.master.File;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseModel {
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_code", unique = true)
    private String code;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public File getFile() {return file;}

    public void setFile(File file) {this.file = file;}

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}
}
