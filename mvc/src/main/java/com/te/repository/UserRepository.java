package com.te.repository;

//02/25/2019


import com.te.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> { //interface is a standard, which has no implementation on the method, but has decoration
    List<User> findAll();
//    User findById(Long id);
    User findByUsername(String username);
    List<User> findByFirstName(String firstName);

    @Query("Select user from User user LEFT JOIN FETCH user.images where user.id=?1") //?1 indicates the first argument, Long Id
    Optional<User> findByIdWithImages(Long id);

//    @Query("Select user from User user LEFT JOIN FETCH user.images where c.id=?2") //?1 indicates the first argument, Long ImageId
//    Optional<User> findByIdWithImages(Long Id, Long ImageId);

//    @Query("Select user from User user LEFT JOIN FETCH user.myJobs where user.id=?1")
//    Optional<User> findByIdWithMyJobs(Long id);

//    @Query("Select u from User u JOIN FETCH u.myJobs where u.id=?1")
//    Optional<User> findByIdEager(Long id);

    @Query("SELECT u FROM User u where u.username=?1 OR u.email=?1")
    User findByEmailOrUsername(String usernameOrEmail);

//    @Query("SELECT u from User u JOIN FETCH u.authorities where u.id=?1")
//    User findUserByAuthority (String authority);

}
