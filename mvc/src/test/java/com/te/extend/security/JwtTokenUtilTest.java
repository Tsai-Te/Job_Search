package com.te.extend.security;

import com.te.config.AppConfig;
import com.te.domain.User;
import com.te.repository.UserRepository;
import com.te.service.UserService;
import org.junit.Before;
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

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class JwtTokenUtilTest {
    private String username="tsai.te";
    private String email="te@gmail.com";
    private String password="7";
    private String firstName="te";
    private String lastName="tsai";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    private User newUser;

    @Before
    public void setUp() throws ParseException {
        newUser=new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setLastName(lastName);
        newUser.setFirstName(firstName);
        String date="03/12/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        newUser.setDateOfBirth(dateOfBirth);
//        userRepository.save(newUser);
    }

    @Test
    @Transactional
    public void generateTokenTest() {
        userService.createUser(newUser);
        String expectedToken = jwtTokenUtil.generateToken(newUser);
        String[] testToken=expectedToken.split("\\.");
        assertEquals(3,testToken.length);
    }

    @Test
    @Transactional
    public void getUsernameFromTokenTest() {
        userService.createUser(newUser);
        String expectedToken=jwtTokenUtil.generateToken(newUser);
        String actualUsername=jwtTokenUtil.getUsernameFromToken(expectedToken);
        assertEquals(newUser.getUsername(),actualUsername);
//        assertNotNull(actualUsername);
    }
}
