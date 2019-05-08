package com.te.api;

import com.te.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MiscController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageService imageService;



}
