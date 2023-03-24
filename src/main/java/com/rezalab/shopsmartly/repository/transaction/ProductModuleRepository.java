package com.rezalab.shopsmartly.repository.transaction;

import com.rezalab.shopsmartly.model.transaction.ProductModule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductModuleRepository extends CrudRepository<ProductModule, Long> {
    List<ProductModule> findByProductCodeAndActiveTrue(String code);

    List<ProductModule> findByProductNameAndActiveTrue(String name);

    List<ProductModule> findByStoreNameAndActiveTrue(String name);

    List<ProductModule> findByStoreCodeAndActiveTrue(String code);

    List<ProductModule> findByPriceAndActiveTrue(BigDecimal price);

    List<ProductModule> findByDateAndActiveTrue(LocalDate date);

    @Query(value = "select r from ProductModule r where r.active=true and (lower(r.product.name) like %:sSearch% or " +
            "lower(r.product.code) like %:sSearch% or lower(r.store.name) like %:sSearch% or " +
            "lower(r.store.code) like %:sSearch%)")
    Page<ProductModule> getPageable(@Param("sSearch") String sSearch);
}
