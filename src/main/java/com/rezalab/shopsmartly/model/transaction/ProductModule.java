package com.rezalab.shopsmartly.model.transaction;

import com.rezalab.shopsmartly.model.base.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_module")
public class ProductModule extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date")
    private LocalDate date;

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public Store getStore() {return store;}

    public void setStore(Store store) {this.store = store;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}
}
