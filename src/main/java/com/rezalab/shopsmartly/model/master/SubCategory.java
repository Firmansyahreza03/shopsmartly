package com.rezalab.shopsmartly.model.master;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "product_sub_category")
public class SubCategory extends BaseModel {
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }
}
