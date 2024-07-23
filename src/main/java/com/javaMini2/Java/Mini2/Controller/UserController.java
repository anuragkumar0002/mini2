package com.javaMini2.Java.Mini2.Controller;

import com.javaMini2.Java.Mini2.Entity.User;
import com.javaMini2.Java.Mini2.Service.UserService;
import com.javaMini2.Java.Mini2.Utility.Validator;
import com.javaMini2.Java.Mini2.Utility.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<List<User>> createUsers(@RequestParam int size) {

        List<User> userdata = userService.createUsers(size);

        System.out.println(userdata);


        return ResponseEntity.ok(userdata);

    }


    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam String sortType,
                                               @RequestParam String sortOrder,
                                               @RequestParam int limit,
                                               @RequestParam int offset) {

        Validator typeValidator = ValidatorFactory.getValidator("alphabet");
        Validator orderValidator = ValidatorFactory.getValidator("alphabet");

        if (!typeValidator.validate(sortType)) {
            throw new IllegalArgumentException("Invalid sort type");
        }

        if (!orderValidator.validate(sortOrder)) {
            throw new IllegalArgumentException("Invalid sort order");
        }

        return ResponseEntity.ok(userService.getUsers(sortType, sortOrder, limit, offset));


    }


}

