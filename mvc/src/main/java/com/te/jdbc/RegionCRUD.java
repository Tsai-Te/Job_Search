package com.te.jdbc;

import com.te.domain.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionCRUD extends CrudRepository<Region, Long> {
    List<Region> findAll();
    List<Region> findByState();
    List<Region> findByCity();
    List<Region> findByZipCode();
}
