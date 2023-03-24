package com.rezalab.shopsmartly.repository.user;

import com.rezalab.shopsmartly.model.user.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByEmailAndActiveTrue(String email);
}
