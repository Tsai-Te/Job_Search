package com.te.service;

import com.te.domain.MyJob;
import com.te.repository.MyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyJobService {
    @Autowired
    private MyJobRepository myJobRepository;

    @Transactional
    public List<MyJob> findAll(){
        return myJobRepository.findAll();
    }

    @Transactional
    public List<MyJob> findBySavedJobs(String savedJobs){
        return myJobRepository.findBySavedJobs(savedJobs);
    }

    @Transactional
    public MyJob generateMyJob(MyJob myJob) {return myJobRepository.save(myJob);}

    @Transactional
    public MyJob findById(Long id){return myJobRepository.findById(id).get();}

//    @Transactional
//    public List<MyJob> findByUser_id(Long id){return myJobRepository.findByUser_id(id);}

//    @Transactional
//    public List<MyJob> findMyJobsByUserId(Long id){return myJobRepository.findByUser_Id(id);}

}
