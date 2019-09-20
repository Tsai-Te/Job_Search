package com.te.service;

import com.te.domain.Authority;
import com.te.domain.User;
import com.te.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public Authority findById(Long id) throws Exception{
        if(id==null){
            throw new NullPointerException("");
        }
        return authorityRepository.findById(id).get();
    }

    @Transactional
    public List<Authority> findAll(){
        return authorityRepository.findAll();
    }

    @Transactional
    public List<Authority> findAuthoritiesByUser_Id (Long user_Id) throws Exception{
        if(user_Id==null){
            throw new NullPointerException("");
        }
        return authorityRepository.findAuthoritiesByUser_Id(user_Id);
    }

    @Transactional
    public Authority addAuthority(String role, User user){
        Authority authority=new Authority();
        authority.setAuthority(role);
        authority.setUser(user);
        return authorityRepository.save(authority);
    }


}
