package com.te.repository;

import com.te.domain.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Long> {
    List<Position> findAll();
    List<Position> findByAuditor(String auditor);
    List<Position> findByManager(String manager);
    List<Position> findByEngineer(String engineer);
}
