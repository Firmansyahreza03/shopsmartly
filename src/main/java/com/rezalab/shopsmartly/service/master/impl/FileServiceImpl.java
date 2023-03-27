package com.rezalab.shopsmartly.service.master.impl;

import com.rezalab.shopsmartly.model.master.File;
import com.rezalab.shopsmartly.repository.master.FileRepository;
import com.rezalab.shopsmartly.service.master.FileService;
import com.rezalab.shopsmartly.service.master.wrapper.FileWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    private File toEntity(FileWrapper wrapper) {
        File model = new File();
        BeanUtils.copyProperties(wrapper, model);
        return model;
    }

    private FileWrapper toWrapper(File entity) {
        FileWrapper wrapper = new FileWrapper();
        BeanUtils.copyProperties(entity, wrapper);
        return wrapper;
    }

    private List<FileWrapper> toWrapperList(List<File> modelList) {
        List<FileWrapper> wrapperList = new ArrayList<>();
        if (!modelList.isEmpty() && modelList != null) {
            for (File model: modelList) {
                wrapperList.add(toWrapper(model));
            }
        }
        return wrapperList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public FileWrapper save(FileWrapper wrapper) throws Exception {
        return toWrapper(fileRepository.save(toEntity(wrapper)));
    }

    @Override
    public FileWrapper findById(Long pk) throws Exception {
        Optional<File> optModel = fileRepository.findById(pk);
        return optModel.map(this::toWrapper).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean delete(Long pk) throws Exception {
        FileWrapper wrapper = findById(pk);
        if (wrapper != null) {
            wrapper.setActive(false);
            fileRepository.save(toEntity(wrapper));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FileWrapper> findAll() throws Exception {
        return toWrapperList((List<File>) fileRepository.findAll());
    }

    @Override
    public void deleteALl() throws Exception {
        // not implemented
    }

    @Override
    public Page<FileWrapper> getPageable(String sSearch) throws Exception {
        // not implemented
        return null;
    }

    @Override
    public FileWrapper findByFileConverted(String fileConverted) throws Exception {
        Optional<File> model = fileRepository.findByFileConvertedAndActiveTrue(fileConverted);
        return model.map(this::toWrapper).orElse(null);
    }
}
