package com.rezalab.shopsmartly.repository.master;

import com.rezalab.shopsmartly.model.master.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT r FROM Category r where r.active=true AND (lower(r.name) like %:sSearch% or lower(r.code) like %:sSearch%)")
    List<Category> getPageable(@Param("sSearch") String sSearch);

    Optional<Category> findByNameAndActiveTrue(String name);

    Optional<Category> findByCodeAndActiveTrue(String code);
}
