package com.javaMini2.Java.Mini2.Utility;

import com.javaMini2.Java.Mini2.Entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class NameOddSortingStrategy implements SortingStrategy {
    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .filter(user -> user.getName().length() % 2 != 0)
                .collect(Collectors.toList());
    }
}
