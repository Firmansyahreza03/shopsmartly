package com.rezalab.shopsmartly.service.user;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.user.Wrapper.UserInfoWrapper;

import java.time.LocalDate;
import java.util.List;

public interface UserInfoService extends BaseService<UserInfoWrapper, Long> {
    List<UserInfoWrapper> findByJoinDate(LocalDate date);
}
