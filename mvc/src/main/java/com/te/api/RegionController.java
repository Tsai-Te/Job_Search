package com.te.api;

import com.te.domain.Region;
import com.te.repository.RegionRepository;
import com.te.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody //activate json viewResolver
@RequestMapping(value = {"/api/regions", "/api/region"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegionController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RegionService regionService;

    @RequestMapping(method = RequestMethod.POST)
    public Region generateRegion(@RequestBody Region region){
        return regionService.generateRegion(region);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Region findById(@PathVariable("id") Long id){
        return regionService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Region> getJobList(){
        logger.debug("job list:");
        return regionService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"city"})
    public List<Region> findByCity(@RequestParam("city") String city){
        logger.debug("parameter is:"+city);
        return regionService.findByCity(city);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"state"})
    public List<Region> findByState(@RequestParam("state") String state){
        logger.debug("parameter is:"+state);
        return regionService.findByState(state);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"zipCode"})
    public List<Region> findByZipCode(@RequestParam("zipCode") int zipCode){
        logger.debug("parameter is:"+zipCode);
        return regionService.findByZipCode(zipCode);
    }

}
