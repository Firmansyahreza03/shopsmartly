package com.rezalab.shopsmartly.service.transaction.wrapper;

import com.rezalab.shopsmartly.service.base.wrapper.BaseWrapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductModuleWrapper extends BaseWrapper {
    private static final long serialVersionUID = 5013890898486380051L;

    private Long productId;
    private String productName;
    private String productCode;
    private Long storeId;
    private String storeName;
    private String storeCode;
    private BigDecimal price;
    private LocalDate date;

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public String getStoreName() { return storeName; }

    public void setStoreName(String storeName) { this.storeName = storeName; }

    public Long getProductId() { return productId; }

    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductCode() { return productCode; }

    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Long getStoreId() { return storeId; }

    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public String getStoreCode() { return storeCode; }

    public void setStoreCode(String storeCode) { this.storeCode = storeCode; }
}
