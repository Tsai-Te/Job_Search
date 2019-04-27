package com.te.service;

import com.te.config.AppConfig;
import com.te.domain.Position;
import com.te.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class PositionServiceTest {
    private String manager="regional manager";
    private String auditor="auditor";
    private String engineer="civil engineer";

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    @Transactional
    public void findByAuditorTest(){
        Position newPosition=new Position();
        newPosition.setAuditor(auditor);
        positionRepository.save(newPosition);
        List<Position> actualPosition=positionService.findByAuditor(newPosition.getAuditor());
        assertEquals(1,actualPosition.size());
    }

}
