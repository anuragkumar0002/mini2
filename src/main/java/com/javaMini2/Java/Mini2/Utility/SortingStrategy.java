package com.javaMini2.Java.Mini2.Utility;

import com.javaMini2.Java.Mini2.Entity.User;

import java.util.List;
import java.util.stream.Collectors;

public interface SortingStrategy {
    List<User> sort(List<User> users);
}

