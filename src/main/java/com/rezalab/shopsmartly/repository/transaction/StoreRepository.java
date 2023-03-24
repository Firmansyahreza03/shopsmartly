package com.rezalab.shopsmartly.repository.transaction;

import com.rezalab.shopsmartly.model.transaction.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    Store findByNameAndActiveTrue(String name);

    Store findByCodeAndActiveTrue(String code);

    @Query(value = "select r from Store r where r.active=true and (lower(r.address) like %:sSearch% or " +
            "lower(r.secondAddress) like %:sSearch%)")
    Store findByAddress(@Param("sSearch") String address);

    @Query(value = "select r from Store r where r.active=true and (lower(r.name) like %:sSearch% or " +
            "lower(r.code) like %:sSearch% or lower(r.address) like %:sSearch% or " +
            "lower(r.secondAddress) like %:sSearch%)")
    Page<Store> getPageable(@Param("sSearch") String sSearch);
}