package com.te.service;

import com.te.config.AppConfig;
import com.te.domain.Position;
import com.te.domain.Region;
import com.te.repository.PositionRepository;
import com.te.repository.RegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class RegionServiceTest {
    private String city="Vienna";
    private String state="VA";
    private int zipCode=22181;

    private String manager="regional manager";
    private String auditor="auditor";
    private String engineer="civil engineer";
//    private Region region= new Region();

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    @Transactional
    public void findByCityTest(){
        Region expectedRegion=new Region();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        regionRepository.save(expectedRegion);
        List<Region> actualRegion=regionService.findByCity(expectedRegion.getCity());
        Region region=actualRegion.get(0);
//        assertEquals(1,actualRegion.size());
        assertNotNull(city);
        assertEquals(expectedRegion.getId(),region.getId());
        for (Region region1 : actualRegion){
            assertEquals(expectedRegion.getCity(), region1.getCity());
        }
    }

    @Test
    @Transactional
    public void findByStateTest(){
        Region expectedRegion=new Region();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        regionRepository.save(expectedRegion);
        List<Region> actualRegion=regionService.findByState(expectedRegion.getState());
        assertNotNull(actualRegion);
        assertEquals(1,actualRegion.size());
        assertEquals(expectedRegion.getId(),actualRegion.get(0).getId());
        for (Region region : actualRegion){
            assertEquals(expectedRegion.getId(), region.getId());
        }
    }

    @Test
    @Transactional
    public void generateRegionTest(){
        Region newRegion=new Region();
        newRegion.setCity(city);
        newRegion.setState(state);
        newRegion.setZipCode(zipCode);
//        regionRepository.save(newRegion);
        regionService.generateRegion(newRegion);
        Region actualRegion=regionService.findById(newRegion.getId());
        assertNotNull(newRegion);
        assertEquals(newRegion.getId(),actualRegion.getId());
    }

    @Test
    @Transactional
    public void findAllTest(){
        Region expectedRegion=new Region();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        regionRepository.save(expectedRegion);
        List<Region> actualRegion=regionService.findAll();
        Region region=actualRegion.get(0);
        assertEquals(1,actualRegion.size());
        assertEquals(expectedRegion.getId(),region.getId());
        for (Region region1 : actualRegion){
            assertEquals(expectedRegion.getId(), region1.getId());
        }
    }

    @Test
    @Transactional
    public void findByZipCodeTest(){
        Region expectedRegion=new Region();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        regionRepository.save(expectedRegion);
        List<Region> actualRegion=regionService.findByZipCode(expectedRegion.getZipCode());
        assertNotNull(zipCode);
        assertEquals(1,actualRegion.size());
        assertEquals(expectedRegion.getId(),actualRegion.get(0).getId());
        for (Region region :actualRegion){
            assertEquals(expectedRegion.getZipCode(),region.getZipCode());
        }
    }

    @Test
    @Transactional
    public void findByIdTest(){
        Region expectedRegion=new Region();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        regionRepository.save(expectedRegion);
        Region actualRegion=regionService.findById(expectedRegion.getId());

//        assertNotNull();
        assertEquals(expectedRegion.getId(),actualRegion.getId());
    }

    @Test
    @Transactional
    public void findByIdEagerTest(){
        Region expectedRegion=new Region();
        Position expectedPosition=new Position();
        expectedRegion.setCity(city);
        expectedRegion.setState(state);
        expectedRegion.setZipCode(zipCode);
        expectedPosition.setAuditor(auditor);
        expectedPosition.setEngineer(engineer);
        expectedPosition.setManager(manager);
        regionRepository.save(expectedRegion);
        expectedPosition.setRegion(expectedRegion);
        positionRepository.save(expectedPosition);

        Region actualRegion=regionService.findByIdEager(expectedRegion.getId());
//        Position actualPosition=positionService.findById(expectedPosition.getId());
//        actualPosition.setRegion(expectedRegion);

        assertEquals(expectedRegion.getId(),actualRegion.getId());
        assertEquals(expectedRegion.getPositions(),actualRegion.getPositions());
    }
}
