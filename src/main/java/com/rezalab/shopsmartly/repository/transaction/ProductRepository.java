package com.rezalab.shopsmartly.repository.transaction;

import com.rezalab.shopsmartly.model.transaction.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByNameAndActiveTrue(String name);

    Optional<Product> findByCodeAndActiveTrue(String code);

    Optional<Product> findByFileNameAndActiveTrue(String fileName);

    Optional<Product> findByCategoryNameAndActiveTrue(String name);

    Optional<Product> findByCategoryCodeAndActiveTrue(String code);

    @Query(value = "select r from Product r where r.active=true and (lower(r.name) like %:sSearch% or " +
            "lower(r.code) like %:sSearch% or lower(r.description) like %:sSearch% or " +
            "lower(r.file.name) like %:sSearch or lower(r.category.name) like %:sSearch% or " +
            "lower(r.category.code) like %:sSearch)")
    Page<Product> getPageale(@Param("sSearch") String sSearch);
}
