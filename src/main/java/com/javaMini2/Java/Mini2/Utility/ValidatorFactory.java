package com.javaMini2.Java.Mini2.Utility;

public class ValidatorFactory {
    public static Validator getValidator(String type) {
        if (type.equals("numeric")) {
            return NumericValidator.getInstance();
        } else if (type.equals("alphabet")) {
            return EnglishAlphabetsValidator.getInstance();
        }
        throw new IllegalArgumentException("Invalid validator type");
    }
}
