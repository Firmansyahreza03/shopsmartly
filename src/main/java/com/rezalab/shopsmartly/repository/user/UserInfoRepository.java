package com.rezalab.shopsmartly.repository.user;

import com.rezalab.shopsmartly.model.user.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findByJoinDateAndActiveTrue(LocalDate date);
}
