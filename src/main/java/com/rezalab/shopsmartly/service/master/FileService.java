package com.rezalab.shopsmartly.service.master;

import com.rezalab.shopsmartly.service.base.BaseService;
import com.rezalab.shopsmartly.service.master.wrapper.FileWrapper;

public interface FileService extends BaseService<FileWrapper, Long> {
    FileWrapper findByFileConverted(String fileConverted) throws Exception;
}
