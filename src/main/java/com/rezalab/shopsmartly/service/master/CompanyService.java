package com.rezalab.shopsmartly.service.master;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.master.wrapper.CompanyWrapper;

public interface CompanyService extends BaseService<CompanyWrapper, Long> {
    CompanyWrapper findByName(String name) throws Exception;

    CompanyWrapper findByAddress(String address) throws Exception;

    CompanyWrapper findByZip(String zip) throws Exception;

    CompanyWrapper findByPhoneNumber(String phoneNumber) throws Exception;
}
