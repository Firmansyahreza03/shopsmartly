package com.rezalab.shopsmartly.service.user;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.user.wrapper.UserAccountWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends BaseService<UserAccountWrapper, Long>, UserDetailsService {
    UserAccountWrapper findByEmail(String email);
}
