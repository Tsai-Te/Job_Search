package com.te.service;

import com.te.domain.Image;
import com.te.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public Image generateImage(Image image){
        return imageRepository.save(image);
    }

    @Transactional
    public List<Image> findAll(){return imageRepository.findAll();}

    @Transactional
    public Image findById(Long id){return imageRepository.findById(id).get();}

    @Transactional
    public List<Image> findByUser_id(Long id){return imageRepository.findByUser_id(id);}
}
