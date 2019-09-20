package com.te.service;

import com.te.domain.Position;
import com.te.repository.PositionRepository;
import com.te.util.PositionComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public List<Position> findAll(){
        return positionRepository.findAll();
    }

    @Transactional
    public List<Position> findByAuditor (String auditor) throws Exception{
        if(auditor==null){
            throw new NullPointerException("");
        }
        return positionRepository.findByAuditor(auditor);
    }

    @Transactional
    public List<Position> findByManager (String manager) throws Exception{
        if(manager==null){
            throw new NullPointerException("");
        }
        return positionRepository.findByManager(manager);
    }

    @Transactional
    public List<Position> findByEngineer (String engineer){
        return positionRepository.findByEngineer(engineer);
    }

    @Transactional
    public Position generatePosition (Position position){return positionRepository.save(position);}

    @Transactional
    public Position findById (Long id){return positionRepository.findById(id).get();}

    @Transactional
    public List<Position> descendPosition(List<Position> compare) {
        Collections.sort(compare, new PositionComparator());
        return compare;
    }
}
