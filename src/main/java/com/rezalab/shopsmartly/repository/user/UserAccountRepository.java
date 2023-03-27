package com.rezalab.shopsmartly.repository.user;

import com.rezalab.shopsmartly.model.user.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmailAndActiveTrue(String email);
}
