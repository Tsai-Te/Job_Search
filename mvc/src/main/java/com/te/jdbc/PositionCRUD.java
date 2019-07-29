package com.te.jdbc;

import com.te.domain.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionCRUD extends CrudRepository<Position,Long> {
    List<Position> findAll();
    List<Position> save();
    List<Position> findByAuditor();
    List<Position> findByEngineer();
    List<Position> findByManager();
}
