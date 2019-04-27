package com.te.api;

import com.te.domain.MyJob;
import com.te.domain.User;
import com.te.service.MyJobService;
import com.te.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.geolatte.geom.M;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody //activate json viewResolver
@RequestMapping(value = {"/api/myJobs", "/api/myJob"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MyJobController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private MyJobService myJobService;

    @Autowired
    private UserService userService;

    String test;

    @RequestMapping(method = RequestMethod.POST)
    public MyJob generateMyJob(@RequestBody MyJob myJob){
        return myJobService.generateMyJob(myJob);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MyJob findById(@PathVariable("id") Long id){
        logger.debug("myJobs path variable is:"+id);
//         test ="test";
        return myJobService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MyJob> findAll(){
//        test
        logger.debug("list myJob");
        return myJobService.findAll();
    }

//    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
//    public User findMyJobsByUserId(@PathVariable("user_id") Long userId){
//        logger.debug("parameter is:"+userId);
//        return userService.findByIdEager(userId);
//    }

    @RequestMapping(method = RequestMethod.GET, params = {"savedJobs"})
    public List<MyJob> findBySavedJobs (@RequestParam("savedJobs") String savedJobs){
        logger.debug("parameter is:"+savedJobs);
        return myJobService.findBySavedJobs(savedJobs);
    }

//    todo write put method
}
