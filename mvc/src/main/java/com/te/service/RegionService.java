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
    public List<Region> findByCity(String city){
        return regionRepository.findByCity(city);
    }

    @Transactional
    public List<Region> findByState(String state){
        return regionRepository.findByState(state);
    }

    @Transactional
    public List<Region> findByZipCode(int zipCode){
        return regionRepository.findByZipCode(zipCode);
    }

    @Transactional
    public Region generateRegion(Region region){
        return regionRepository.save(region);
    }

    @Transactional
    public Region findById(Long id){
        return regionRepository.findById(id).get();
    }

    @Transactional
    public Region findByIdEager(Long id){
        Region result=regionRepository.findByIdEager(id).get();
        return result;
    }
}
