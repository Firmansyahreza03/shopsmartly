package com.rezalab.shopsmartly.repository.master;

import com.rezalab.shopsmartly.model.master.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByCodeAndActiveTrue(String code);

    Role findByNameAndActiveTrue(String name);

    @Query(value = "select r from Role r where r.active=true and (lower(r.name) like %:sSearch% or " +
            "lower(r.code) like %:sSearch%)")
    Page<Role> getPageable(@Param("sSearch") String sSearch);
}
