package com.te.api;

import com.te.domain.Image;
import com.te.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody //activate json viewResolver
@RequestMapping(value = {"/api/images", "/api/image"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.POST)
    public Image generateImage(@RequestBody Image image){
        return imageService.generateImage(image);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Image> getImageList(){
        logger.debug("list image:");
        return imageService.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Image findById(@PathVariable("id") Long id){
        logger.debug("image path variable is:"+id);
        return imageService.findById(id);
    }


}
