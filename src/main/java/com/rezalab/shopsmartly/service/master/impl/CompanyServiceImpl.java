package com.rezalab.shopsmartly.service.master.impl;

import com.rezalab.shopsmartly.model.master.Company;
import com.rezalab.shopsmartly.repository.master.CompanyRepository;
import com.rezalab.shopsmartly.service.master.CompanyService;
import com.rezalab.shopsmartly.service.master.wrapper.CompanyWrapper;
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
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private Company toEntity(CompanyWrapper wrapper) {
        Company model = new Company();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private CompanyWrapper toWrapper(Company entity) {
        CompanyWrapper wrapper = new CompanyWrapper();
        BeanUtils.copyProperties(entity, wrapper);
        return wrapper;
    }

    private List<CompanyWrapper> toWrapperList(List<Company> modelList) {
        List<CompanyWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (Company model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CompanyWrapper save(CompanyWrapper wrapper) throws Exception {
        return toWrapper(companyRepository.save(toEntity(wrapper)));
    }

    @Override
    public CompanyWrapper findById(Long pk) throws Exception {
        Optional<Company> optModel = companyRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        CompanyWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            companyRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CompanyWrapper> findAll() throws Exception {
        return toWrapperList((List<Company>) companyRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<CompanyWrapper> getPageable(String sSearch) throws Exception {
        try {
            Page<Company> bookPage = companyRepository.getPageable(sSearch);
            List<CompanyWrapper> wrapperList = toWrapperList(bookPage.getContent());
            return new PageImpl<>(wrapperList, null, bookPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public CompanyWrapper findByName(String name) throws Exception {
        Optional<Company> model = companyRepository.findByNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public CompanyWrapper findByAddress(String address) throws Exception {
        Optional<Company> model = companyRepository.findByAddressAndActiveTrue(address);
        if (model.isEmpty()) {
            model = companyRepository.findBySecondAddressAndActiveTrue(address);
        }
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public CompanyWrapper findByZip(String zip) throws Exception {
        Optional<Company> model = companyRepository.findByZipAndActiveTrue(zip);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public CompanyWrapper findByPhoneNumber(String phoneNumber) throws Exception {
        Optional<Company> model = companyRepository.findByPhoneNumberAndActiveTrue(phoneNumber);
        return model.map(this::toWrapper).orElse(null);
    }
}
