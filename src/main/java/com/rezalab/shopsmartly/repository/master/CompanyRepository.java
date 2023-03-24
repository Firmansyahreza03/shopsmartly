package com.rezalab.shopsmartly.repository.master;

import com.rezalab.shopsmartly.model.master.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Query(value = "Select r from Company r where r.active=true and (lower(r.name) like %:sSearch% or " +
            "lower(r.address) like %:Search% or lower(r.secondAddress) like %:sSearch% or lower(r.zip) like " +
            "%:sSearch% or lower(r.phoneNumber) like %:sSearch%)")
    Page<Company> getPageable(@Param("sSearch") String sSearch);
}
