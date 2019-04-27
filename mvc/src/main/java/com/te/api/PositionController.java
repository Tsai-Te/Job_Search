package com.te.api;

import com.te.domain.Position;
import com.te.domain.Region;
import com.te.service.PositionService;
import com.te.service.RegionService;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody //activate json viewResolver
@RequestMapping(value = {"/api/positions", "/api/position"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private PositionService positionService;

    @Autowired
    private RegionService regionService;

    @RequestMapping(method = RequestMethod.POST)
    public Position generatePosition (@RequestBody Position position){
        return positionService.generatePosition(position);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Position> getPositionList(){
        logger.debug("position list");
        return positionService.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Position findById(@PathVariable("id") Long id){
        return positionService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"auditor"})
    public List<Position> findByAuditor(@RequestParam("auditor") String auditor){
        logger.debug("parameter is:"+auditor);
        return positionService.findByAuditor(auditor);
    }

    @RequestMapping(method = RequestMethod.GET,params = {"manager"})
    public List<Position> findByManager(@RequestParam("manager") String manager){
        logger.debug("parameter is:"+manager);
        return positionService.findByManager(manager);
    }

    @RequestMapping(method = RequestMethod.GET,params = {"engineer"})
    public List<Position> findByEngineer(@RequestParam("engineer") String engineer){
        logger.debug("parameter is:"+engineer);
        return positionService.findByEngineer(engineer);
    }

    @RequestMapping(value="/region/{region_id}", method=RequestMethod.GET)
    public Region findPositionsByRegionId(@PathVariable("region_id") Long regionId){
        logger.debug("parameter is:"+regionId);
        Region result=regionService.findByIdEager(regionId);
        return result;
    }
}
