package com.te.util;

import com.te.domain.User;

import java.util.Comparator;

public class UserEmailComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return user1.getEmail().compareTo(user2.getEmail());
    }
}
