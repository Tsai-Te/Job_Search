package com.te.util;

import com.te.domain.User;

import java.util.Comparator;

public class UserIdComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getId().intValue()-o2.getId().intValue();
    }
}
