package com.javaMini2.Java.Mini2.Entity;

import lombok.Data;
import java.util.List;

@Data
public class RandomUserResponse {

  private List<User> results;
}