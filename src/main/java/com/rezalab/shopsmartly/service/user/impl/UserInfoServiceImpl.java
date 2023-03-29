package com.rezalab.shopsmartly.service.user.impl;

import com.rezalab.shopsmartly.model.user.UserInfo;
import com.rezalab.shopsmartly.repository.user.UserInfoRepository;
import com.rezalab.shopsmartly.service.user.UserInfoService;
import com.rezalab.shopsmartly.service.user.wrapper.UserInfoWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private UserInfo toEntity(UserInfoWrapper wrapper) {
        UserInfo model = new UserInfo();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private UserInfoWrapper toWrapper(UserInfo model) {
        UserInfoWrapper wrapper = new UserInfoWrapper();
        BeanUtils.copyProperties(model, wrapper);
        return wrapper;
    }

    private List<UserInfoWrapper> toWrapperList(List<UserInfo> modelList) {
        List<UserInfoWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserInfo model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Override
    public List<UserInfoWrapper> findByJoinDate(LocalDate date) {
        List<UserInfoWrapper> wrappers = toWrapperList(userInfoRepository.findByJoinDateAndActiveTrue(date));
        return wrappers;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserInfoWrapper save(UserInfoWrapper wrapper) throws Exception {
        return toWrapper(userInfoRepository.save(toEntity(wrapper)));
    }

    @Override
    public UserInfoWrapper findById(Long pk) throws Exception {
        Optional<UserInfo> optModel = userInfoRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        UserInfoWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            userInfoRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserInfoWrapper> findAll() throws Exception {
        return toWrapperList((List<UserInfo>) userInfoRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public List<UserInfoWrapper> getPageable(String sSearch) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public UserInfoWrapper updateById(Long pk, UserInfoWrapper wrapper) throws Exception {
        // not implemented
        return null;
    }
}
