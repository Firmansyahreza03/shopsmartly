package com.rezalab.shopsmartly.repository.user;

import com.rezalab.shopsmartly.model.user.UserModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModuleRepository extends CrudRepository<UserModule, Long> {

}
