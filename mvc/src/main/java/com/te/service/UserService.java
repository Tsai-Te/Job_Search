package com.te.service;

import com.te.domain.User;
import com.te.repository.UserRepository;
import com.te.util.UserComparator;
import com.te.util.UserIdComparator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;


    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser){
        String encodedPassword=encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        User result=userRepository.save(newUser);
        authorityService.addAuthority("ROLE_REGISTERED_USER",result);
        return result;
    }

//    @Transactional
//    public User setPassword(String password){
//        User user=new User();
//        String encodePassword=encoder.encode(user.getPassword());
//        user.setPassword(encodePassword);
//        User newPassword=userRepository.save(password);
//        return newPassword;
//    }

    @Transactional
    public User generateUser(User newUser){
        String encodedPassword=encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        User result=userRepository.save(newUser);
        authorityService.addAuthority("ROLE_REGISTERED_USER",result);
        return result;
    }

    @Transactional
    public User save(User newUser){
        newUser.setUsername("");
//        userRepository.save(user);
//        return save(user);
        return userRepository.save(newUser);
    }

    @Transactional
    public User findByUsername(String username) throws Exception { //todo ask the difference between NullPointerException and NotFoundException
        if (username==null) {
            throw new NullPointerException("");
        }
        User user=userRepository.findByUsername(username);
        if (user==null) {
            throw new NotFoundException("");
        }
        return user;
    }

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).get();
    } //todo ask get()

    @Transactional
    public List<User> findByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    @Transactional
    public User editUsername(User user, String newUsername){
        user.setUsername(newUsername);
        User result=userRepository.save(user);
        return result;
    }

    @Transactional
    public User editEmailAddress(User user, String newEmailAddress){
        user.setEmail(newEmailAddress);
        return userRepository.save(user);
    }

    @Transactional
    public User editLastName(User user, String lastName){
        user.setLastName(lastName);
        return userRepository.save(user);
    }

//    @Transactional
//    public User findByIdEager(Long Id){
//        return userRepository.findByIdEager(Id).get();
//    }

    @Transactional(readOnly = true) //todo ask what is readOnly
    public User findByEmailOrUsername(String emailOrUsername) throws NotFoundException, NullPointerException {
        if(emailOrUsername==null||"".equals(emailOrUsername.trim())) throw new NullPointerException("Please enter username or email");
        {
            User user = userRepository.findByEmailOrUsername(emailOrUsername);
            if (user == null) {
                user = userRepository.findByEmailOrUsername(emailOrUsername);//todo what is in the bracket : argument, variable
            }
            if (user == null) {
                throw new NotFoundException("No user was found");
            }
            return user;
        }
    }

    @Transactional
    public List<User> descendUsername (List<User> unsortedUsername){
//        Collections.sort(unsortedUsername,new UserComparator());
        Collections.sort(unsortedUsername,new UserIdComparator());
        return unsortedUsername;
    }

    @Transactional
    public List<User> descendLastName (List<User> unsortedLastName) {
        Collections.sort(unsortedLastName);
        return unsortedLastName;
    }

    @Transactional
    public List<User> userComparator (List<User> userComparator) {
        Collections.sort(userComparator, new UserComparator());
        return userComparator;
    }

    @Transactional
    public User editFirstName(User user, String firstName){
        user.setFirstName(firstName);
        return userRepository.save(user);
    }

//    public User findByAuthority(String authority){
//        return userRepository.findUserByAuthority(authority);
//    }
}
