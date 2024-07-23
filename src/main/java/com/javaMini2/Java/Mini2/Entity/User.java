package com.javaMini2.Java.Mini2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String dob;
    private String nationality;
    private String verificationStatus;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;


    public User(Object o, String johnDoe, int i, String male, String s, String us, String verified, LocalDateTime now, LocalDateTime now1) {
    }

    public User() {

    }
}

