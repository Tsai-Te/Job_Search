package com.te.util;

import com.te.domain.Region;

import java.util.Comparator;

public class RegionIdComparator implements Comparator<Region> {
    @Override
    public int compare(Region o1, Region o2) {
        return o1.getId().intValue()-o2.getId().intValue();
    }
}
