package com.rezalab.shopsmartly.repository.master;

import com.rezalab.shopsmartly.model.master.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT r FROM Category r where r.active=true AND (lower(r.name) like %:sSearch% or lower(r.code) like %:sSearch)")
    Page<Category> getPageable(@Param("sSearch") String sSearch);

    Category findByNameAndActiveTrue(String name);

    Category findByCodeAndActiveTrue(String code);
}
