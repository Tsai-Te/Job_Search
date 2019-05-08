package com.te.service;

import com.amazonaws.services.s3.model.S3Object;
import com.te.domain.Image;
import com.te.repository.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

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

    @Transactional
    public Image saveFakeImage(MultipartFile multipartFile) throws ServiceException {
        if(multipartFile == null||multipartFile.isEmpty()) throw new ServiceException("File must not be empty");
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String homeDir = System.getProperty("catalina.base") !=null ? System.getProperty("catalina.base"): null;           //todo ask what is "?"
        Image image=new Image();
        String s3Key = FilenameUtils.getBaseName(multipartFile.getOriginalFilename()+"_"+image.getUuid());
        File localFile = new File(homeDir+s3Key);
        try {
            multipartFile.transferTo(localFile);
            storageService.putObject(s3Key,localFile);
            S3Object s3Object = storageService.getObject(s3Key);
            image.setUrl(storageService.getObjectUrl(s3Object.getKey()));
            image.setExtension(extension);
            return image;
        } catch (IOException e) {
            logger.warn("cannot find the image file");
        }
        return null;
    }
}
