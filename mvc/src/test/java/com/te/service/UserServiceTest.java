package com.te.service;


import com.te.config.AppConfig;
import com.te.domain.Authority;
import com.te.domain.User;
import com.te.repository.AuthorityRepository;
import com.te.repository.UserRepository;
import com.te.util.UserComparator;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserServiceTest {
    private String username="tsai.te";
    private String email="te@gmail.com";
    private String password="7";
    private String firstName="te";
    private String lastName="tsai";
    private String newUsername="cai.te";
    private String newEmailAddress="c.te@gmail.com";
    private String newLastName="cai";
    private String newFirstName="ter";


    @Autowired //if we need autowired, then we have to need these annotations
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional //tear down
    public void findAll() throws ParseException {
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);
        expectedUser.setEmail(email);
        expectedUser.setFirstName(firstName);
        expectedUser.setLastName(lastName);
        String date="04/03/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        userService.createUser(expectedUser);
        List<User> actualUser = userService.findAll();
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getId(),actualUser.get(0).getId());
        for (User user : actualUser){
            assertEquals(expectedUser.getId(), user.getId());
        }
        assertNotNull(password,actualUser.get(0).getPassword());
//        assertEquals(expectedUser.getAuthorities(),actualUser.get(0).getAuthorities());
    }

    @Test
    @Transactional
    public void findByIdTest() throws ParseException {
        User expectedUser=new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date="03/11/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        userRepository.save(expectedUser);
//        assertTrue(false); //todo what is this step for
//        assertNotNull(id);
        User actualUser=userService.findById(expectedUser.getId());
        assertEquals(expectedUser.getId(), actualUser.getId()); //todo where does assertEquals come from
    }

    @Test
    @Transactional //tear down to remove this record
    public void generateUserTest () throws ParseException {
        User newUser=new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setLastName(lastName);
        String date="03/12/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        newUser.setDateOfBirth(dateOfBirth);
//        userRepository.save(newUser);
        userService.generateUser(newUser);
        User actualUser=userService.findById(newUser.getId());
        assertNotNull(newUser);
        assertEquals(newUser.getId(),actualUser.getId());
    }

    @Test
    @Transactional
    public void editUsernameTest() throws ParseException {
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date = "07/31/2019";
        Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        userService.editUsername(expectedUser, newUsername);
        User userWithTheNewUsername=userService.findById(expectedUser.getId());
        assertNotNull(username);
        assertNotNull(newUsername);
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getId(),userWithTheNewUsername.getId());
        assertEquals(expectedUser.getUsername(),userWithTheNewUsername.getUsername());
    }

    @Test
    @Transactional
    public void editEmailAddressTest() throws ParseException {
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date = "07/31/2019";
        Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        userService.editEmailAddress(expectedUser,newEmailAddress);
        User userWithNewEmailAddress=userService.findById(expectedUser.getId());
        assertNotNull(email);
        assertNotNull(newEmailAddress);
        assertEquals(expectedUser.getEmail(),userWithNewEmailAddress.getEmail());
    }

    @Test
    @Transactional
    public void findByUsernameTest() throws Exception {
        User expectedUser=new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date="03/12/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        userRepository.save(expectedUser);
        User actualUser=userService.findByUsername(expectedUser.getUsername());
        assertNotNull(firstName);
        assertEquals(expectedUser.getUsername(),actualUser.getUsername());
    }

    @Test
    @Transactional
    public void findByFirstNameTest() throws ParseException {
        User expectedUser=new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        expectedUser.setFirstName(firstName);
        String date="03/12/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        userRepository.save(expectedUser);
        List<User> actualUser=userService.findByFirstName(expectedUser.getFirstName()); //what's wrong
        assertNotNull(firstName);
        assertEquals(1,actualUser.size());
        assertEquals(expectedUser.getFirstName(),actualUser.get(0).getFirstName());
        for (User user : actualUser){
            assertEquals(firstName, user.getFirstName());
        }
    }

    @Test
    @Transactional
    public void createUserTest() throws ParseException {
        User newUser=new User();
        newUser.setUsername(username);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        String date="03/26/2019";
        Date dateOfBirth=new SimpleDateFormat("MM/dd/yyyy").parse(date);
        newUser.setDateOfBirth(dateOfBirth);
        userService.createUser(newUser);
//        userRepository.save(newUser);
        User actualUser=userService.findById(newUser.getId());

        Authority newAuthority=new Authority();
        newAuthority.setAuthority("ROLE_REGISTERED_USER");
        newAuthority.setUser(newUser);
        authorityRepository.save(newAuthority);
        List<Authority> actualAuthorities=authorityRepository.findByUserId(newUser.getId());
        Authority actualAuthority=actualAuthorities.get(0);

        assertNotNull(newUser);
        assertNotEquals(password,actualUser.getPassword());
        assertEquals(newUser.getId(),actualUser.getId());
//        assertEquals(newAuthority.getId(),actualAuthority.getId());
        assertEquals(newAuthority.getId(),actualAuthorities.get(1).getId());
        for (Authority authority:actualAuthorities){
            assertEquals("ROLE_REGISTERED_USER", authority.getAuthority());
        }
//        assertEquals(1,actualAuthorities.size());
//        assertEquals(newUser.getAuthorities(),actualUser.getAuthorities());
//        assertEquals(newUser.getId(),actualUser.getId());
    }

    @Test
    @Transactional
    public void editLastNameTest() throws ParseException {
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        expectedUser.setFirstName(firstName);
        String date = "03/12/2019";
        Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        userService.editLastName(expectedUser, newLastName);
        User userWithNewLastName = userService.findById(expectedUser.getId());
        assertNotNull(lastName);
        assertNotNull(newLastName);
        assertEquals(expectedUser.getLastName(), userWithNewLastName.getLastName());
    }

    @Test
    @Transactional
    public void editFirstNameTest() throws ParseException {
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setLastName(lastName);
        String date = "03/11/2019";
        Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        expectedUser.setDateOfBirth(dateOfBirth);
        userService.editFirstName(expectedUser, firstName);
        User userWithNewFirstName = userService.findById(expectedUser.getId());
        assertEquals(expectedUser.getId(), userWithNewFirstName.getId());
    }

    @Test
    @Transactional
    public void userComparatorTest() throws ParseException {
        User expectedUser1 = new User();
        expectedUser1.setUsername("abc");
        expectedUser1.setEmail("abc.com");
        expectedUser1.setPassword(password);
        expectedUser1.setFirstName("abcF");
        expectedUser1.setLastName("abcL");
        String date1 = "08/28/2019";
        Date dateOfBirth1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
        expectedUser1.setDateOfBirth(dateOfBirth1);
        userService.createUser(expectedUser1);

        User expectedUser2 = new User();
        expectedUser2.setUsername("def");
        expectedUser2.setEmail("def.com");
        expectedUser2.setPassword(password);
        expectedUser2.setLastName("abcL");
        expectedUser2.setFirstName("defF");
        String date2 = "08/28/2019";
        Date dateOfBirth2 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
        expectedUser2.setDateOfBirth(dateOfBirth2);
        userService.createUser(expectedUser2);

        User expectedUser3 = new User();
        expectedUser3.setUsername("ghi");
        expectedUser3.setEmail("ghi.com");
        expectedUser3.setPassword(password);
        expectedUser3.setLastName("ghiL");
        expectedUser3.setFirstName("ghiF");
        String date3 = "08/28/2019";
        Date dateOfBirth3 = new SimpleDateFormat("MM/dd/yyyy").parse(date3);
        expectedUser3.setDateOfBirth(dateOfBirth3);
        userService.createUser(expectedUser3);

        User expectedUser4 = new User();
        expectedUser4.setUsername("jkl");
        expectedUser4.setEmail("jkl.com");
        expectedUser4.setPassword(password);
        expectedUser4.setLastName("jklL");
        expectedUser4.setFirstName("jklF");
        String date4 = "08/28/2019";
        Date dateOfBirth4 = new SimpleDateFormat("MM/dd/yyyy").parse(date4);
        expectedUser4.setDateOfBirth(dateOfBirth4);
        userService.createUser(expectedUser4);

        List<User> sortedUsers=userService.userComparator(userService.findAll());
        List<User> originalUsers=new ArrayList<>(userService.findAll());
        originalUsers.sort(new UserComparator());
        assertEquals(sortedUsers,originalUsers);

    }

    @Test
    @Transactional
    public void descendUsernameTest() throws ParseException {
        User expectedUser1 = new User();
        expectedUser1.setUsername("abc");
        expectedUser1.setEmail("abc.com");
        expectedUser1.setPassword(password);
        expectedUser1.setFirstName("abcF");
        expectedUser1.setLastName("abcL");
        String date1 = "08/28/2019";
        Date dateOfBirth1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
        expectedUser1.setDateOfBirth(dateOfBirth1);
        userService.createUser(expectedUser1);

        User expectedUser2 = new User();
        expectedUser2.setUsername("def");
        expectedUser2.setEmail("def.com");
        expectedUser2.setPassword(password);
        expectedUser2.setLastName("abcL");
        expectedUser2.setFirstName("defF");
        String date2 = "08/28/2019";
        Date dateOfBirth2 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
        expectedUser2.setDateOfBirth(dateOfBirth2);
        userService.createUser(expectedUser2);

        User expectedUser3 = new User();
        expectedUser3.setUsername("ghi");
        expectedUser3.setEmail("ghi.com");
        expectedUser3.setPassword(password);
        expectedUser3.setLastName("ghiL");
        expectedUser3.setFirstName("ghiF");
        String date3 = "08/28/2019";
        Date dateOfBirth3 = new SimpleDateFormat("MM/dd/yyyy").parse(date3);
        expectedUser3.setDateOfBirth(dateOfBirth3);
        userService.createUser(expectedUser3);

        User expectedUser4 = new User();
        expectedUser4.setUsername("jkl");
        expectedUser4.setEmail("jkl.com");
        expectedUser4.setPassword(password);
        expectedUser4.setLastName("jklL");
        expectedUser4.setFirstName("jklF");
        String date4 = "08/28/2019";
        Date dateOfBirth4 = new SimpleDateFormat("MM/dd/yyyy").parse(date4);
        expectedUser4.setDateOfBirth(dateOfBirth4);
        userService.createUser(expectedUser4);

        List<User> originalUsers = new ArrayList<>(userService.findAll());
        Collections.sort(originalUsers);
        List<User> sortedUsers=userService.descendUsername(userService.findAll());

        assertEquals(originalUsers,sortedUsers);
    }





//    @Test
//    public void findTellerByManager_IdTest(){
//        assertTrue(false);
//    }

//    public void findByFi()
}
