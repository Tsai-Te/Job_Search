package com.te.jdbc;

import com.te.domain.MyJob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyJobCRUD extends CrudRepository<MyJob,Long> {
    List<MyJob> findAll();
    List<MyJob> findBySaveJobs();
}
