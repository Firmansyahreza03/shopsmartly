package com.rezalab.shopsmartly.controller.master;

import com.rezalab.shopsmartly.model.master.File;
import com.rezalab.shopsmartly.service.master.FileService;
import com.rezalab.shopsmartly.service.master.wrapper.FileWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id) throws Exception {
        FileWrapper file = fileService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getExtension());

        byte[] fileInBytes = Base64.getDecoder().decode(file.getFileConverted());
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileInBytes);
    }
}
