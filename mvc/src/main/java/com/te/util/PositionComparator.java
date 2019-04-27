package com.te.util;

import com.te.domain.Position;

import java.util.Comparator;

public class PositionComparator implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        if (o1.getManager().equals(o2.getManager())){
            return o1.getAuditor().compareTo(o2.getAuditor());
        } else if (o1.getAuditor().equals(o2.getAuditor())){
            return o1.getEngineer().compareTo(o2.getEngineer());
        } else
            return o1.getManager().compareTo(o2.getManager());
    }
}
