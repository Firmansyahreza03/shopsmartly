package com.rezalab.shopsmartly.service.transaction;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.transaction.wrapper.ProductModuleWrapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductModuleService extends BaseService<ProductModuleWrapper, Long> {
    List<ProductModuleWrapper> findByProductCode(String code) throws Exception;

    List<ProductModuleWrapper> findByProductName(String name) throws Exception;

    List<ProductModuleWrapper> findByStoreCode(String code) throws Exception;

    List<ProductModuleWrapper> findByStoreName(String name) throws Exception;

    List<ProductModuleWrapper> findByPrice(BigDecimal price) throws Exception;

    List<ProductModuleWrapper> findByDate(LocalDate date) throws Exception;
}
