package com.te.jdbc;

import com.te.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCRUD extends CrudRepository<User, Long> {
    List<User> findAll();
    User findById();
    User save();
    User findByUsername();
    List<User> findByFirstName();
    List<User> findByLastName();
}
