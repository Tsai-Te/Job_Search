package com.te.extend.security;

import com.te.domain.Authority;
import com.te.domain.User;
import com.te.service.AuthorityService;
import com.te.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    //    private static final Logger logger= LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailOrUsername) {//todo ask about this method
        logger.debug(emailOrUsername + "is trying to log in from spring security");
        User domainUser = null;
        try {
            domainUser = userService.findByEmailOrUsername(emailOrUsername); //username is argument
        } catch (NotFoundException | NullPointerException repositoryProblem) {
            logger.debug("catch AuthenticationServiceException from AuthenticationProvider");
        }
//            if (domainUser == null) {
//                //todo exception handle
//            }
            //TODO add authority into user
            // rodo ask why does wamt not need this?
        List<Authority> userAuthority = authorityService.findAuthoritiesByUser_Id(domainUser.getId());
//        Collection<GrantedAuthority> authorities= Utils.getAuthorities(userAuthority);
        domainUser.setAuthorities(userAuthority);
        return domainUser;
    }

}
