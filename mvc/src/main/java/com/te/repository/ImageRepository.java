package com.te.repository;

import com.te.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();
    List<Image> findByUser_id(Long id);
//    Image findByS3Key(String s3Key);

}
