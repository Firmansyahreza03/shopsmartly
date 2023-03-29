package com.rezalab.shopsmartly.service.master.impl;

import com.rezalab.shopsmartly.model.master.Role;
import com.rezalab.shopsmartly.repository.master.RoleRepository;
import com.rezalab.shopsmartly.service.master.RoleService;
import com.rezalab.shopsmartly.service.master.wrapper.RoleWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private Role toEntity(RoleWrapper wrapper) {
        Role model = new Role();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private RoleWrapper toWrapper(Role entity) {
        RoleWrapper wrapper = new RoleWrapper();
        BeanUtils.copyProperties(entity, wrapper);
        return wrapper;
    }

    private List<RoleWrapper> toWrapperList(List<Role> modelList) {
        List<RoleWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (Role model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RoleWrapper save(RoleWrapper wrapper) throws Exception {
        return toWrapper(roleRepository.save(toEntity(wrapper)));
    }

    @Override
    public RoleWrapper findById(Long pk) throws Exception {
        Optional<Role> optModel = roleRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        RoleWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            roleRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RoleWrapper> findAll() throws Exception {
        return toWrapperList((List<Role>) roleRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public List<RoleWrapper> getPageable(String sSearch) throws Exception {
        try {
            List<Role> bookPage = roleRepository.getPageable(sSearch);
            List<RoleWrapper> wrapperList = toWrapperList(bookPage);
            return wrapperList;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public RoleWrapper findByCode(String code) throws Exception {
        Optional<Role> model = roleRepository.findByCodeAndActiveTrue(code);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public RoleWrapper findByName(String name) throws Exception {
        Optional<Role> model = roleRepository.findByNameAndActiveTrue(name);
        return model.map(this::toWrapper).orElse(null);
    }

    @Override
    public RoleWrapper updateById(Long pk, RoleWrapper wrapper) throws Exception {
        Optional<Role> model = roleRepository.findById(pk);
        if (model != null && !model.isEmpty()) {
            Role modelOld = model.get();
            modelOld.setCode(wrapper.getCode());
            modelOld.setName(wrapper.getName());
            modelOld.setUpdatedBy(null);
            modelOld.setVersion(wrapper.getVersion());
            return toWrapper(roleRepository.save(modelOld));
        } else {
            return save(wrapper);
        }
    }

}
