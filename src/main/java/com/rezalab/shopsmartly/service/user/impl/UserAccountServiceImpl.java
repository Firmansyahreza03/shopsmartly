package com.rezalab.shopsmartly.service.user.impl;

import com.rezalab.shopsmartly.model.master.Role;
import com.rezalab.shopsmartly.model.user.UserAccount;
import com.rezalab.shopsmartly.repository.master.RoleRepository;
import com.rezalab.shopsmartly.repository.user.UserAccountRepository;
import com.rezalab.shopsmartly.service.user.UserAccountService;
import com.rezalab.shopsmartly.service.user.wrapper.UserAccountWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RoleRepository roleRepository;

    private UserAccount toEntity(UserAccountWrapper wrapper) {
        UserAccount model = new UserAccount();

        if (wrapper.getRoleCode() != null) {
            Optional<Role> role = roleRepository.findByCodeAndActiveTrue(wrapper.getRoleCode());
            model.setRole(role.orElse(null));
        }

        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private UserAccountWrapper toWrapper(UserAccount model) {
        UserAccountWrapper wrapper = new UserAccountWrapper();

        if (model.getRole() != null) {
            wrapper.setRoleCode(model.getRole().getCode());
        }

        BeanUtils.copyProperties(model, wrapper);
        return wrapper;
    }

    private List<UserAccountWrapper> toWrapperList(List<UserAccount> modelList) {
        List<UserAccountWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserAccount model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Override
    public UserAccountWrapper findByEmail(String email) {
       Optional<UserAccount> model = userAccountRepository.findByEmailAndActiveTrue(email);
       return model.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserAccountWrapper save(UserAccountWrapper wrapper) throws Exception {
        return toWrapper(userAccountRepository.save(toEntity(wrapper)));
    }

    @Override
    public UserAccountWrapper findById(Long pk) throws Exception {
        Optional<UserAccount> model = userAccountRepository.findById(pk);
        return model.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        UserAccountWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            userAccountRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserAccountWrapper> findAll() throws Exception {
        return toWrapperList((List<UserAccount>) userAccountRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public List<UserAccountWrapper> getPageable(String sSearch) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public UserAccountWrapper updateById(Long pk, UserAccountWrapper wrapper) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccountWrapper account = findByEmail(email);
        if(account == null) {
            throw new RuntimeException("Pengguna tidak ditemukan");
        }
        return new User(email, account.getPassword(), new ArrayList<>());
    }
}
