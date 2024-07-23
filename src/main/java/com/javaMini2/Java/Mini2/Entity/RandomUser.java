package com.javaMini2.Java.Mini2.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RandomUser {
    private String gender;
    private String firstName;
    private String lastName;
    private String nationality;
    private int age;

    public RandomUser(String gender, String firstName, String lastName, String nationality, int age) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
    }



    public RandomUser() {

    }



}
