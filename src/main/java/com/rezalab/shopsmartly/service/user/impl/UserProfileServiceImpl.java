package com.rezalab.shopsmartly.service.user.impl;

import com.rezalab.shopsmartly.model.master.File;
import com.rezalab.shopsmartly.model.user.UserProfile;
import com.rezalab.shopsmartly.repository.master.FileRepository;
import com.rezalab.shopsmartly.repository.user.UserProfileRepository;
import com.rezalab.shopsmartly.service.user.UserProfileService;
import com.rezalab.shopsmartly.service.user.wrapper.UserProfileWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private FileRepository fileRepository;

    private UserProfile toEntity(UserProfileWrapper wrapper) {
        UserProfile model = new UserProfile();
        BeanUtils.copyProperties(wrapper, model);
        Optional<File> fileOpt = null;
        if (wrapper.getFileId() != null) {
            fileOpt = fileRepository.findById(wrapper.getFileId());
        } else if (wrapper.getFileConverted() != null) {
            fileOpt = fileRepository.findByFileConvertedAndActiveTrue(wrapper.getFileConverted());
        }
        model.setFile(fileOpt.orElse(null));
        return model;
    }

    private UserProfileWrapper toWrapper(UserProfile model) {
        UserProfileWrapper wrapper = new UserProfileWrapper();
        BeanUtils.copyProperties(model, wrapper);
        if (model.getFile() != null) {
            wrapper.setFileId(model.getFile().getId());
            wrapper.setFileConverted(model.getFile().getFileConverted());
        }
        return wrapper;
    }

    private List<UserProfileWrapper> toWrapperList(List<UserProfile> modelList) {
        List<UserProfileWrapper> wrapperList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserProfile model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserProfileWrapper save(UserProfileWrapper wrapper) throws Exception {
        return toWrapper(userProfileRepository.save(toEntity(wrapper)));
    }

    @Override
    public UserProfileWrapper findById(Long pk) throws Exception {
        Optional<UserProfile> optModel = userProfileRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        UserProfileWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            userProfileRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserProfileWrapper> findAll() throws Exception {
        return toWrapperList((List<UserProfile>) userProfileRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public List<UserProfileWrapper> getPageable(String sSearch) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public UserProfileWrapper updateById(Long pk, UserProfileWrapper wrapper) throws Exception {
        // not implemented
        return null;
    }
}
