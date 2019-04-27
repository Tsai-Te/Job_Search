package com.te.util;

import com.te.domain.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.getLastName().equals(o2.getLastName())) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        } else if (o1.getFirstName().equals(o1.getFirstName())) {
            return o1.getEmail().compareTo(o2.getEmail());
        } else
            return o1.getLastName().compareTo(o2.getLastName());
    }
//        return o1.getId().intValue()-o2.getId().intValue();

}
