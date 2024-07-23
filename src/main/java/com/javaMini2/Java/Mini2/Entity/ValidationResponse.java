package com.javaMini2.Java.Mini2.Entity;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse {
    private String gender;
    private List<String> nationalities;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getNationalities() {
        return nationalities;
    }

    public void setNationalities(List<String> nationalities) {
        this.nationalities = nationalities;
    }

}
