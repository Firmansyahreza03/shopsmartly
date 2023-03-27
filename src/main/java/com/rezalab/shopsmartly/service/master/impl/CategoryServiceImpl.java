package com.rezalab.shopsmartly.service.master.impl;

import com.rezalab.shopsmartly.model.master.Category;
import com.rezalab.shopsmartly.repository.master.CategoryRepository;
import com.rezalab.shopsmartly.service.master.CategoryService;
import com.rezalab.shopsmartly.service.master.wrapper.CategoryWrapper;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category toEntity(CategoryWrapper wrapper) {
        Category model = new Category();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private CategoryWrapper toWrapper(Category model) {
        CategoryWrapper wrapper = new CategoryWrapper();
        BeanUtils.copyProperties(model, wrapper);
        return wrapper;
    }

    private List<CategoryWrapper> toWrapperList(List<Category> modelList) {
        List<CategoryWrapper> wrapperList = new ArrayList<>();
        if(modelList != null && !modelList.isEmpty()) {
            for (Category model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CategoryWrapper save(CategoryWrapper wrapper) throws Exception {
        return toWrapper(categoryRepository.save(toEntity(wrapper)));
    }

    @Override
    public CategoryWrapper findById(Long pk) throws Exception {
        Optional<Category> optModel = categoryRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        CategoryWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            categoryRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CategoryWrapper> findAll() throws Exception {
        return toWrapperList((List<Category>) categoryRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<CategoryWrapper> getPageable(String sSearch) throws Exception {
        try {
            Page<Category> bookPage = categoryRepository.getPageable(sSearch);
            List<CategoryWrapper> wrapperList = toWrapperList(bookPage.getContent());
            return new PageImpl<>(wrapperList, null, bookPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public CategoryWrapper findByName(String name) throws Exception {
        Optional<Category> model = categoryRepository.findByNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public CategoryWrapper findByCode(String code) throws Exception {
        Optional<Category> model = categoryRepository.findByCodeAndActiveTrue(code);
        return model.map(this::toWrapper).orElse(null);
    }
}
