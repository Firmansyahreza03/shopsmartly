package com.rezalab.shopsmartly.service.transaction;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.transaction.wrapper.ProductWrapper;

public interface ProductService extends BaseService<ProductWrapper, Long> {
    ProductWrapper findByName(String name) throws Exception;

    ProductWrapper findByCode(String code) throws Exception;

    ProductWrapper findByCategoryName(String name) throws Exception;

    ProductWrapper findByCategoryCode(String code) throws Exception;
}
