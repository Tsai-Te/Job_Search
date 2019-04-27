package com.te.util;

import com.te.domain.Region;

import java.util.Comparator;

public class RegionComparator implements Comparator<Region> {
    @Override
    public int compare(Region o1, Region o2) {
        if (o1.getCity().equals(o2.getCity())) {
            return o1.getState().compareTo(o2.getState());
        } else {
            return o1.getCity().compareTo(o2.getCity());
        }
    }
}
