package com.rezalab.shopsmartly.repository.user;

import com.rezalab.shopsmartly.model.user.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
}
