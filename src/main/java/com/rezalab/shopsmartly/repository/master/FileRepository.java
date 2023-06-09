package com.rezalab.shopsmartly.repository.master;

import com.rezalab.shopsmartly.model.master.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByFileConvertedAndActiveTrue(String fileConverted);
}
