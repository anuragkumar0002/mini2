package com.javaMini2.Java.Mini2.Utility;

public class EnglishAlphabetsValidator implements Validator {
    private static EnglishAlphabetsValidator instance;

    private EnglishAlphabetsValidator() {
    }

    public static EnglishAlphabetsValidator getInstance() {
        if (instance == null) {
            instance = new EnglishAlphabetsValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
