package com.javaMini2.Java.Mini2.Utility;

public class NumericValidator implements Validator {
    private static NumericValidator instance;

    private NumericValidator() {}

    public static NumericValidator getInstance() {
        if (instance == null) {
            instance = new NumericValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String input) {
        return input.matches("\\d+");
    }
}

