package com.rezalab.shopsmartly.service.master;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.master.wrapper.CategoryWrapper;

public interface CategoryService extends BaseService<CategoryWrapper, Long> {
    CategoryWrapper findByName(String name) throws Exception;

    CategoryWrapper findByCode(String code) throws Exception;
}
