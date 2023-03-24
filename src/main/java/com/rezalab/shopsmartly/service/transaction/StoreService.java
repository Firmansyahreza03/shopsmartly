package com.rezalab.shopsmartly.service.transaction;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.transaction.wrapper.StoreWrapper;

public interface StoreService extends BaseService<StoreWrapper, Long> {
    StoreWrapper findByName(String name) throws Exception;

    StoreWrapper findByCode(String code) throws Exception;

    StoreWrapper findByAddress(String address, String secondAddress) throws Exception;
}
