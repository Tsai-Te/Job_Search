package com.te.repository;

import com.te.domain.Position;
import com.te.domain.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAll();
    List<Region> findByState(String state);
    List<Region> findByZipCode(int zipCode);
    List<Region> findByCity(String city);

    @Query("SELECT r FROM Region r JOIN FETCH r.positions WHERE r.id=?1")
    Optional<Region> findByIdEager(Long id);
}
