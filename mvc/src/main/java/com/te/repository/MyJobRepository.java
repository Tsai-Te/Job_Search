package com.te.repository;

import com.te.domain.MyJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyJobRepository extends CrudRepository<MyJob, Long> {
    List<MyJob> findAll();
    List<MyJob> findBySavedJobs(String savedJobs);
//    List<MyJob> findByUser_Id(Long id);
//    List<MyJob> findMyJobsByUser_Id(Long id);
}
