package com.rezalab.shopsmartly.service.user;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.user.Wrapper.UserAccountWrapper;

public interface UserAccountService extends BaseService<UserAccountWrapper, Long> {
    UserAccountWrapper findByEmail(String email);
}
