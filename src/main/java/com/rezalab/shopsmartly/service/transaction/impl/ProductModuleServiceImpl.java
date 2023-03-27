package com.rezalab.shopsmartly.service.transaction.impl;

import com.rezalab.shopsmartly.model.transaction.Product;
import com.rezalab.shopsmartly.model.transaction.ProductModule;
import com.rezalab.shopsmartly.model.transaction.Store;
import com.rezalab.shopsmartly.repository.transaction.ProductModuleRepository;
import com.rezalab.shopsmartly.repository.transaction.ProductRepository;
import com.rezalab.shopsmartly.repository.transaction.StoreRepository;
import com.rezalab.shopsmartly.service.transaction.ProductModuleService;
import com.rezalab.shopsmartly.service.transaction.wrapper.ProductModuleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductModuleServiceImpl implements ProductModuleService {

    @Autowired
    private ProductModuleRepository productModuleRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductModule toEntity(ProductModuleWrapper wrapper) {
        ProductModule model = new ProductModule();
        model.setId(wrapper.getId());

        if (wrapper.getStoreId() != null) {
            Optional<Store> store = storeRepository.findById(wrapper.getStoreId());
            if (store.isPresent() && !store.isEmpty() && store.get().getActive() == true) {
                model.setStore(store.get());
            } else {
                model.setStore(null);
            }
        }

        if (wrapper.getProductId() != null) {
            Optional<Product> product = productRepository.findById(wrapper.getProductId());
            if (product.isPresent() && !product.isEmpty() && product.get().getActive() == true) {
                model.setProduct(product.get());
            } else {
                model.setProduct(null);
            }
        }

        model.setDate(wrapper.getDate());
        model.setPrice(wrapper.getPrice());
        model.setCreatedAt(wrapper.getCreatedAt());
        model.setCreatedBy(wrapper.getCreatedBy());
        model.setUpdatedAt(wrapper.getUpdatedAt());
        model.setUpdatedBy(wrapper.getUpdatedBy());
        model.setActive(wrapper.getActive());
        model.setVersion(wrapper.getVersion());
        return model;
    }

    private ProductModuleWrapper toWrapper(ProductModule entity) {
        ProductModuleWrapper wrapper = new ProductModuleWrapper();
        wrapper.setId(wrapper.getId());

        if (entity.getStore() != null) {
            wrapper.setStoreId(entity.getStore().getId());
            wrapper.setStoreName(entity.getStore().getName());
            wrapper.setStoreCode(entity.getStore().getCode());
        }

        if (entity.getProduct() != null) {
            wrapper.setProductId(entity.getProduct().getId());
            wrapper.setProductName(entity.getProduct().getName());
            wrapper.setProductCode(entity.getProduct().getCode());
        }

        wrapper.setDate(wrapper.getDate());
        wrapper.setPrice(wrapper.getPrice());
        wrapper.setCreatedAt(wrapper.getCreatedAt());
        wrapper.setCreatedBy(wrapper.getCreatedBy());
        wrapper.setUpdatedAt(wrapper.getUpdatedAt());
        wrapper.setUpdatedBy(wrapper.getUpdatedBy());
        wrapper.setActive(wrapper.getActive());
        wrapper.setVersion(wrapper.getVersion());
        return wrapper;
    }

    private List<ProductModuleWrapper> toWrapperList(List<ProductModule> modelList) {
        List<ProductModuleWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (ProductModule model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductModuleWrapper save(ProductModuleWrapper wrapper) throws Exception {
        return toWrapper(productModuleRepository.save(toEntity(wrapper)));
    }

    @Override
    public ProductModuleWrapper findById(Long pk) throws Exception {
        Optional<ProductModule> optModel = productModuleRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        ProductModuleWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            productModuleRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ProductModuleWrapper> findAll() throws Exception {
        return toWrapperList((List<ProductModule>)  productModuleRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<ProductModuleWrapper> getPageable(String sSearch) throws Exception {
        try {
            Page<ProductModule> bookPage = productModuleRepository.getPageable(sSearch);
            List<ProductModuleWrapper> wrapperList = toWrapperList(bookPage.getContent());
            return new PageImpl<>(wrapperList, null, bookPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ProductModuleWrapper> findByProductCode(String code) throws Exception {
        List<ProductModule> models = productModuleRepository.findByProductCodeAndActiveTrue(code);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }

    @Override
    public List<ProductModuleWrapper> findByProductName(String name) throws Exception {
        List<ProductModule> models = productModuleRepository.findByProductNameAndActiveTrue(name);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }

    @Override
    public List<ProductModuleWrapper> findByStoreCode(String code) throws Exception {
        List<ProductModule> models = productModuleRepository.findByStoreCodeAndActiveTrue(code);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }

    @Override
    public List<ProductModuleWrapper> findByStoreName(String name) throws Exception {
        List<ProductModule> models = productModuleRepository.findByStoreNameAndActiveTrue(name);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }

    @Override
    public List<ProductModuleWrapper> findByPrice(BigDecimal price) throws Exception {
        List<ProductModule> models = productModuleRepository.findByPriceAndActiveTrue(price);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }

    @Override
    public List<ProductModuleWrapper> findByDate(LocalDate date) throws Exception {
        List<ProductModule> models = productModuleRepository.findByDateAndActiveTrue(date);
        List<ProductModuleWrapper> wrapperList = toWrapperList(models);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList;
        }
    }
}
