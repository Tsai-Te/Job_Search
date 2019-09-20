package com.te.api;

import com.te.domain.Authority;
import com.te.domain.User;
import com.te.repository.AuthorityRepository;
import com.te.repository.UserRepository;
import com.te.service.AuthorityService;
import com.te.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"api/admin", "api/admins"})
public class AdminController {
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user/{id}","/users/{id}"}, method = RequestMethod.PUT)
    private User elevateAdminRole(@PathVariable("id") Long id) throws Exception{
        if(id==null){
            throw new NullPointerException("");
        }
        logger.debug("User path variable is:" +id);
        User user=userService.findById(id);
        Authority authorityAdmin=new Authority();
        authorityAdmin.setAuthority("ROLE_ADMIN");
        authorityAdmin.setUser(user);
        authorityRepository.save(authorityAdmin);
        return user;
    }

//    @RequestMapping(value = {"/user/user/{id}","/user/users/{id}"}, method = RequestMethod.PUT) //todo ask how to get /user and /users
//    private User elevateManagerRole (@PathVariable("id") Long id){
//        logger.debug("User path variable is:" +id);
//        User user=userService.findById(id);
//        Authority authorityManager=new Authority();
//        authorityManager.setAuthority("Manager");
//        authorityManager.setUser(user);
//        authorityRepository.save(authorityManager);
//        return user;
//    }

    @RequestMapping(value = "/user/president",method = RequestMethod.PUT,params={"username"})
    private User elevatePresidentRole(@RequestParam("username") String username) throws Exception {
        logger.debug("Parameter is:" +username);
        User user=userService.findByUsername(username);
        Authority authorityPresident=new Authority();
        authorityPresident.setAuthority("President");
        authorityPresident.setUser(user);
        authorityRepository.save(authorityPresident);
        return user;
    }


}
