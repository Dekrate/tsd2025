package org.example.tsddemo;

import java.util.Collection;

public class UserFilter {

    //TODO - Task 2 - Finish the below method
    Collection<User> filter(Collection<User> users) {
        return users.stream()
                .filter(user -> !user.getName().startsWith("A"))
                .filter(user -> !user.getEmail().contains("@"))
                .filter(user -> !user.getPhone().startsWith("+48"))
                .toList();
    }
}
