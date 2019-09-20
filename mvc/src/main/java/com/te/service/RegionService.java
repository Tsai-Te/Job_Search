package com.te.service;

import com.te.domain.Region;
import com.te.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Transactional
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Transactional
    public List<Region> findByCity(String city) throws Exception{
        if(city==null){
            throw new NullPointerException("");
        }
        return regionRepository.findByCity(city);
    }

    @Transactional
    public List<Region> findByState(String state) throws Exception{
        if(state==null){
            throw new NullPointerException("");
        }
        return regionRepository.findByState(state);
    }

    @Transactional
    public List<Region> findByZipCode(Integer zipCode) throws Exception{
        if(zipCode==null){
            throw new NullPointerException("");
        }
        return regionRepository.findByZipCode(zipCode);
    }

    @Transactional
    public Region generateRegion(Region region) throws Exception{
        if(region==null){
            throw new NullPointerException("");
        }
        return regionRepository.save(region);
    }

    @Transactional
    public Region findById(Long id) throws Exception{
        if(id==null){
            throw new NullPointerException("");
        }
        return regionRepository.findById(id).get();
    }

    @Transactional
    public Region findByIdEager(Long id) throws Exception{
        if(id==null){
            throw new NullPointerException("");
        }
        Region result=regionRepository.findByIdEager(id).get();
        return result;
    }
}
