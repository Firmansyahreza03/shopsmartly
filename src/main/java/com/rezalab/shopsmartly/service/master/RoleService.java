package com.rezalab.shopsmartly.service.master;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.master.wrapper.RoleWrapper;

public interface RoleService extends BaseService<RoleWrapper, Long> {
    RoleWrapper findByCode(String code) throws Exception;

    RoleWrapper findByName(String name) throws Exception;
}
