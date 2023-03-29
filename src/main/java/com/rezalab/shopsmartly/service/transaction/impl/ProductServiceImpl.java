package com.rezalab.shopsmartly.service.transaction.impl;

import com.rezalab.shopsmartly.model.transaction.Product;
import com.rezalab.shopsmartly.repository.transaction.ProductRepository;
import com.rezalab.shopsmartly.service.transaction.ProductService;
import com.rezalab.shopsmartly.service.transaction.wrapper.ProductWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Product toEntity(ProductWrapper wrapper) {
        Product model = new Product();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private ProductWrapper toWrapper(Product model) {
        ProductWrapper wrapper = new ProductWrapper();
        BeanUtils.copyProperties(model, wrapper);
        return wrapper;
    }

    private List<ProductWrapper> toWrapperList(List<Product> modelList) {
        List<ProductWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (Product model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductWrapper save(ProductWrapper wrapper) throws Exception {
        return toWrapper(productRepository.save(toEntity(wrapper)));
    }

    @Override
    public ProductWrapper findById(Long pk) throws Exception {
        Optional<Product> optModel = productRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        ProductWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            productRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ProductWrapper> findAll() throws Exception {
        return toWrapperList((List<Product>) productRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public List<ProductWrapper> getPageable(String sSearch) throws Exception {
        try {
            List<Product> bookPage = productRepository.getPageable(sSearch);
            List<ProductWrapper> wrapperList = toWrapperList(bookPage);
            return wrapperList;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ProductWrapper updateById(Long pk, ProductWrapper wrapper) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public ProductWrapper findByName(String name) throws Exception {
        Optional<Product> model = productRepository.findByNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public ProductWrapper findByCode(String code) throws Exception {
        Optional<Product> model = productRepository.findByCodeAndActiveTrue(code);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public ProductWrapper findByCategoryName(String name) throws Exception {
        Optional<Product> model = productRepository.findByCategoryNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public ProductWrapper findByCategoryCode(String code) throws Exception {
        Optional<Product> model = productRepository.findByCategoryCodeAndActiveTrue(code);
        return model.map(this::toWrapper).orElse(null);
    }
}
