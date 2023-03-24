package com.rezalab.shopsmartly.service.base;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T, Z> {

    T save(T entity) throws Exception;

    T findById(Z pk) throws Exception;

    Boolean delete(Z pk) throws Exception;

    List<T> findAll() throws Exception;

    void deleteALl() throws Exception;

    Page<T> getPageable(String sSearch);
}
