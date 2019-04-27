package com.te.extend.security;

import com.te.config.AppConfig;
import com.te.domain.User;
import com.te.extend.security.UserDetailsServiceImpl;
import com.te.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserDetailsServiceImplTest {
    private String username="tsai.te";
    private String email="te@gmail.com";
    private String password="7";
    private String firstName="te";
    private String lastName="tsai";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @Transactional
    public void loadUserByUsernameTest() throws ParseException {
        User expectedUser=new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        String date="03/11/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        expectedUser.setFirstName(firstName);
        expectedUser.setLastName(lastName);
        userRepository.save(expectedUser);
        UserDetails actualUser=userDetailsService.loadUserByUsername(expectedUser.getUsername());
        assertEquals(expectedUser.getUsername(),actualUser.getUsername());
    }
}
