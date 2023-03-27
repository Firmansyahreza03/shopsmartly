package com.rezalab.shopsmartly.service.user.impl;

import com.rezalab.shopsmartly.model.user.UserAccount;
import com.rezalab.shopsmartly.model.user.UserInfo;
import com.rezalab.shopsmartly.model.user.UserModule;
import com.rezalab.shopsmartly.model.user.UserProfile;
import com.rezalab.shopsmartly.repository.user.UserAccountRepository;
import com.rezalab.shopsmartly.repository.user.UserInfoRepository;
import com.rezalab.shopsmartly.repository.user.UserModuleRepository;
import com.rezalab.shopsmartly.repository.user.UserProfileRepository;
import com.rezalab.shopsmartly.service.user.UserModuleService;
import com.rezalab.shopsmartly.service.user.wrapper.UserModuleWrapper;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserModuleServiceImpl implements UserModuleService {

    @Autowired
    private UserModuleRepository userModuleRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    private UserModule toEntity(UserModuleWrapper wrapper) {
        UserModule model = new UserModule();
        model.setActive(wrapper.getActive());
        model.setId(wrapper.getId());
        model.setCreatedAt(wrapper.getCreatedAt());
        model.setCreatedBy(wrapper.getCreatedBy());
        model.setUpdatedAt(wrapper.getUpdatedAt());
        model.setUpdatedBy(wrapper.getUpdatedBy());
        model.setVersion(wrapper.getVersion());

        if (wrapper.getUserAccountId() != null) {
            Optional<UserAccount> account = userAccountRepository.findById(wrapper.getUserAccountId());
            model.setUserAccount(account.orElse(null));
        }

        if (wrapper.getUserInfoId() != null) {
            Optional<UserInfo> info = userInfoRepository.findById(wrapper.getUserInfoId());
            model.setUserInfo(info.orElse(null));
        }

        if (wrapper.getUserProfileId() != null) {
            Optional<UserProfile> profile = userProfileRepository.findById(wrapper.getUserProfileId());
            model.setUserProfile(profile.orElse(null));
        }

        return model;
    }

    private UserModuleWrapper toWrapper(UserModule model) {
        UserModuleWrapper wrapper = new UserModuleWrapper();
        if (model.getUserAccount() != null) {
            wrapper.setUserAccountId(model.getUserAccount().getId());
            wrapper.setEmail(model.getUserAccount().getEmail());
        }

        if (model.getUserInfo() != null) {
            wrapper.setUserInfoId(model.getUserInfo().getId());
        }

        if (model.getUserProfile() != null) {
            wrapper.setUserProfileId(model.getUserProfile().getId());
        }

        wrapper.setId(model.getId());
        wrapper.setCreatedBy(model.getCreatedBy());
        wrapper.setCreatedAt(model.getCreatedAt());
        wrapper.setUpdatedAt(model.getUpdatedAt());
        wrapper.setUpdatedBy(model.getUpdatedBy());
        wrapper.setActive(model.getActive());
        wrapper.setVersion(model.getVersion());

        return wrapper;
    }

    private List<UserModuleWrapper> toWrapperList(List<UserModule> modelList) {
        List<UserModuleWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserModule model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserModuleWrapper save(UserModuleWrapper wrapper) throws Exception {
        return toWrapper(userModuleRepository.save(toEntity(wrapper)));
    }

    @Override
    public UserModuleWrapper findById(Long pk) throws Exception {
        Optional<UserModule> optModel = userModuleRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        UserModuleWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            userModuleRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserModuleWrapper> findAll() throws Exception {
        return toWrapperList((List<UserModule>) userModuleRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<UserModuleWrapper> getPageable(String sSearch) throws Exception {
        // not implemented
        return null;
    }
}
