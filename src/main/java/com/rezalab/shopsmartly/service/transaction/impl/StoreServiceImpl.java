package com.rezalab.shopsmartly.service.transaction.impl;

import com.rezalab.shopsmartly.model.transaction.Store;
import com.rezalab.shopsmartly.repository.transaction.StoreRepository;
import com.rezalab.shopsmartly.service.transaction.StoreService;
import com.rezalab.shopsmartly.service.transaction.wrapper.StoreWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    private Store toEntity(StoreWrapper wrapper) {
        Store model = new Store();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private StoreWrapper toWrapper(Store model) {
        StoreWrapper wrapper = new StoreWrapper();
        BeanUtils.copyProperties(model, wrapper);
        return wrapper;
    }

    private List<StoreWrapper> toWrapperList(List<Store> modelList) {
        List<StoreWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (Store model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public StoreWrapper save(StoreWrapper wrapper) throws Exception {
        return toWrapper(storeRepository.save(toEntity(wrapper)));
    }

    @Override
    public StoreWrapper findById(Long pk) throws Exception {
        Optional<Store> optModel = storeRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        StoreWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            storeRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<StoreWrapper> findAll() throws Exception {
        return toWrapperList((List<Store>) storeRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<StoreWrapper> getPageable(String sSearch) throws Exception {
        try {
            Page<Store> bookPage = storeRepository.getPageable(sSearch);
            List<StoreWrapper> wrapperList = toWrapperList(bookPage.getContent());
            return new PageImpl<>(wrapperList, null, bookPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public StoreWrapper findByName(String name) throws Exception {
        Optional<Store> model = storeRepository.findByNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public StoreWrapper findByCode(String code) throws Exception {
        Optional<Store> model = storeRepository.findByCodeAndActiveTrue(code);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public StoreWrapper findByAddress(String address, String secondAddress) throws Exception {
        Optional<Store> model = storeRepository.findByAddress(address);
        if (model == null && model.isEmpty()) {
            model = storeRepository.findByAddress(secondAddress);
        }
        return model.map(this::toWrapper).orElse(null);
    }
}
