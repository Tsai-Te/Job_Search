package com.te.api;

import com.te.extend.security.ResponseToken;
import com.te.domain.User;
import com.te.extend.security.JwtTokenUtil;
import com.te.extend.security.RestAuthenticationRequest;
import com.te.service.UserService;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody //activate json viewResolver
@RequestMapping (value = {"/api/users", "/api/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController  { //todo ask about BaseController
    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

//    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    @RequestMapping(method=RequestMethod.POST)
    public User generateUser(@RequestBody User user){
        return userService.generateUser(user);
    }

    @RequestMapping(value="/signUp",method = RequestMethod.POST)
    public User signUp(@RequestBody User user){
        User result=userService.generateUser(user);
        return result;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<User> getUserList(){ //if void, should not have any return type, such as List<User>
        logger.debug("list users");
        return userService.findAll();
    }

    //    @RequestMapping(method = RequestMethod.GET)//todo ask why this is incorrect
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id){
        logger.debug("user path variable is:"+id);
        return userService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET,params = {"firstName"}) //"params" is needed when we have multiple same API, such as /firstName, so we user "params" to seperate them. It must match with "@RequestParam("firstName")".
    public List<User> findByFirstName(@RequestParam("firstName") String firstName){
        logger.debug("parameter is:"+firstName);
        return userService.findByFirstName(firstName);
    }

    @RequestMapping(method = RequestMethod.GET, params={"username"})
    public User findByUsername(String username) throws Exception {
        logger.debug("parameter is"+username);
        return userService.findByUsername(username);
    }

    @RequestMapping (value="/login", method = RequestMethod.POST) //todo write an api for login by username or email
    @ResponseBody
    public ResponseEntity<?> login (@RequestBody RestAuthenticationRequest restAuthenticationRequest ){ //todo ask what is ResponseEntity
        String username = restAuthenticationRequest.getUsername();
        String password = restAuthenticationRequest.getPassword();
//        logger.info("Login username is:" + " " +username);
//        logger.info("Login password is:" + " " +encoder.encode(password));
        logger.info("Login successfully");

        try {
            Authentication notFullyAuthenticate = new UsernamePasswordAuthenticationToken(username, password);
            final Authentication authentication=authenticationManager.authenticate(notFullyAuthenticate); //todo ask what is this
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try {
                final UserDetails userDetails = userService.findByUsername(username);
                final String token = jwtTokenUtil.generateToken(userDetails);

                ResponseToken tokenToString=new ResponseToken();
                tokenToString.setToken(token);
                return ResponseEntity.ok(tokenToString);
//                HashMap<String, String> tokenToString=new HashMap<>();
//                tokenToString.put("Token",token);
//                return ResponseEntity.ok(tokenToString);


//                return ResponseEntity.ok(new JwtAuthenticationResponse(token));

            }catch (Exception e){
                logger.error("cannot find username or email",e);
                return ResponseEntity.notFound().build();
//                return null;
            }
        }catch (AuthenticationException ex) {
            logger.error("login error", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
        }
    }

    @RequestMapping (value={"/login/admin","/login/admins"}, method = RequestMethod.POST) //todo write an api for login by username or email
    @ResponseBody
    public ResponseEntity<?> adminLogin (@RequestBody RestAuthenticationRequest restAuthenticationRequest){ //todo ask what is ResponseEntity
        String username=restAuthenticationRequest.getUsername();
        String password=restAuthenticationRequest.getPassword();
        logger.info("Login successfully");
        try {
            Authentication notFullyAuthenticate = new UsernamePasswordAuthenticationToken(username, password);

            final Authentication authentication=authenticationManager.authenticate(notFullyAuthenticate);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try {
                final UserDetails userDetails = userService.findByUsername(username);
                final String token = jwtTokenUtil.generateToken(userDetails);
                HashMap<String, String> tokenToString=new HashMap<>();
                tokenToString.put("AdminToken",token);
                return ResponseEntity.ok(tokenToString);
//                return ResponseEntity.ok(new JwtAuthenticationResponse(token));

            }catch (Exception e){
                logger.error("cannot find username or email",e);
                return ResponseEntity.notFound().build();
//                return null;
            }
        }catch (AuthenticationException ex) {
            logger.error("login error", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
        }
    }

    @RequestMapping(value = "/username/sort", method=RequestMethod.GET,params = {"username"})
    public List<User> descendUsername(@RequestParam("username") String username){
        List<User> unsortedUsername=userService.findAll();
        List<User> sortedUsername=userService.descendUsername(unsortedUsername);
        return sortedUsername;
    }

    @RequestMapping(value="/lastName/sort", method=RequestMethod.GET,params = {"lastName"})
    public List<User> descendLastName(@RequestParam("lastName") String lastName){
        List<User> unsortedLastName=userService.findAll();
        List<User> sortedLastName=userService.descendLastName(unsortedLastName);
        return sortedLastName;
    }

//    public List<User> userComparator(String lastName){
//        List<User> unsortedLastName=userService.findAll();
//
//
//    }

//    @RequestMapping(value = "/firstName", method = RequestMethod.GET,params = {"name"}) //todo ask what is "params="//
//    public List<User> asfa(@RequestParam("name") String firstName){
//        logger.debug("parameter is:"+firstName);
//        return userService.findByFirstName(firstName);
//    }

    //todo setpassword
//    @RequestMapping(value = "/password", method = RequestMethod.PATCH)
//    private User setPassword(@RequestParam ("password") String password) {
//        logger.debug("User" +password);
////        enter email through an api
////        api will generate a password
////        user get the password to login with email
////        once user login, it will enter new password to serpassword
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user=userService.findByUsername(username);
//        user.setPassword(password);
////        user.setPassword("1");
////        user.setUser(user);
//        userService.save(user);
//        return user;
//    }


}
