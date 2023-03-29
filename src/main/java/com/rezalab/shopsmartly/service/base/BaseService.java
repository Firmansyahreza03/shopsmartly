package com.rezalab.shopsmartly.service.base;

import java.util.List;

public interface BaseService<T, Z> {

    T save(T wrapper) throws Exception;

    T findById(Z pk) throws Exception;

    Boolean delete(Z pk) throws Exception;

    List<T> findAll() throws Exception;

    void deleteALl() throws Exception;

    List<T> getPageable(String sSearch) throws Exception;

    T updateById(Z pk, T wrapper) throws Exception;
}
