package com.javaMini2.Java.Mini2.Utility;

public class SortingStrategyFactory {
    public static SortingStrategy getSortingStrategy(String sortType, String sortOrder) {
        if (sortType == null || sortOrder == null) {
            throw new IllegalArgumentException("Sort type and order must be provided");
        }

        sortType = sortType.toLowerCase();
        sortOrder = sortOrder.toLowerCase();

        if (sortType.equals("age") && sortOrder.equals("even")) {
            return new AgeEvenSortingStrategy();
        } else if (sortType.equals("name") && sortOrder.equals("odd")) {
            return new NameOddSortingStrategy();
        } else {
            throw new IllegalArgumentException("Invalid sorting strategy type");
        }
    }
}