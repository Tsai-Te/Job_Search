package com.te.service;

import com.te.config.AppConfig;
import com.te.domain.Authority;
import com.te.domain.User;
import com.te.repository.AuthorityRepository;
import com.te.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class AuthorityServiceTest {
    private String authority="guest";

    private String username="tsai.te";
    private String email="te@gmail.com";
    private String password="7";
    private String firstName="te";
    private String lastName="tsai";

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void findByAuthorityTest(){
        Authority expectedAuthority=new Authority();
        expectedAuthority.setAuthority(authority);
        authorityRepository.save(expectedAuthority);
        Authority actualAuthority=authorityService.findById(expectedAuthority.getId());
        assertEquals(expectedAuthority.getId(),actualAuthority.getId());
    }

    @Test
    @Transactional
    public void addAuthorityTest() throws ParseException {
        User expectedUser=new User();
        Authority expectedAuthority=new Authority();

        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date="03/27/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);

        expectedAuthority.setAuthority(authority);
        expectedAuthority.setUser(expectedUser);

        authorityRepository.save(expectedAuthority);
        userRepository.save(expectedUser);

//        Authority authority=authorityService.addAuthority("guest",expectedUser);
//
        List<Authority> actualAuthorities=authorityService.findAuthoritiesByUser_Id(expectedUser.getId());
        Authority actualAuthority=actualAuthorities.get(0);

        entityManager.flush();
        entityManager.refresh(expectedUser);

        for(Authority newAuthority : actualAuthorities){
            assertEquals(newAuthority.getAuthority(),authority);
        }
        assertEquals(expectedAuthority.getAuthority(),actualAuthority.getAuthority());
        assertEquals(expectedAuthority.getId(),actualAuthority.getId());
        assertEquals(expectedAuthority.getUser(),actualAuthority.getUser());
    }
}
