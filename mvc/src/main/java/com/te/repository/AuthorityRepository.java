package com.te.repository;

import com.te.domain.Authority;
import com.te.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority,Long> {
    List<Authority> findAll();

    List<Authority> findByUserId(Long userId);

    List<Authority> findAuthoritiesByUser_Id (Long userId);

}
