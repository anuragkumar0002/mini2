package com.javaMini2.Java.Mini2;//package com.javaMini2.Java.Mini2.Controller;

import com.javaMini2.Java.Mini2.Controller.UserController;
import com.javaMini2.Java.Mini2.Entity.User;
import com.javaMini2.Java.Mini2.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createUsers_Success() {

        int size = 3;
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setName("John Doe");
        user1.setAge(25);
        user1.setGender("male");
        user1.setDob("1995-10-10");
        user1.setNationality("US");
        user1.setVerificationStatus("VERIFIED");
        user1.setDateCreated(LocalDateTime.now());
        user1.setDateModified(LocalDateTime.now());

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setAge(28);
        user2.setGender("female");
        user2.setDob("1993-08-12");
        user2.setNationality("US");
        user2.setVerificationStatus("VERIFIED");
        user2.setDateCreated(LocalDateTime.now());
        user2.setDateModified(LocalDateTime.now());

        User user3 = new User();
        user3.setName("Jim Beam");
        user3.setAge(35);
        user3.setGender("male");
        user3.setDob("1986-11-01");
        user3.setNationality("US");
        user3.setVerificationStatus("VERIFIED");
        user3.setDateCreated(LocalDateTime.now());
        user3.setDateModified(LocalDateTime.now());

        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userService.createUsers(size)).thenReturn(users);


        ResponseEntity<List<User>> responseEntity = userController.createUsers(size);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
        verify(userService, times(1)).createUsers(size);
    }

    @Test
    void getUsers_Success() {
        // Arrange
        String sortType = "name";
        String sortOrder = "asc";
        int limit = 10;
        int offset = 0;
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setName("John Doe");
        user1.setAge(25);
        user1.setGender("male");
        user1.setDob("1995-10-10");
        user1.setNationality("US");
        user1.setVerificationStatus("VERIFIED");
        user1.setDateCreated(LocalDateTime.now());
        user1.setDateModified(LocalDateTime.now());

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setAge(28);
        user2.setGender("female");
        user2.setDob("1993-08-12");
        user2.setNationality("US");
        user2.setVerificationStatus("VERIFIED");
        user2.setDateCreated(LocalDateTime.now());
        user2.setDateModified(LocalDateTime.now());

        User user3 = new User();
        user3.setName("Jim Beam");
        user3.setAge(35);
        user3.setGender("male");
        user3.setDob("1986-11-01");
        user3.setNationality("US");
        user3.setVerificationStatus("VERIFIED");
        user3.setDateCreated(LocalDateTime.now());
        user3.setDateModified(LocalDateTime.now());

        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userService.getUsers(sortType, sortOrder, limit, offset)).thenReturn(users);

        // Act
        ResponseEntity<List<User>> responseEntity = userController.getUsers(sortType, sortOrder, limit, offset);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
        verify(userService, times(1)).getUsers(sortType, sortOrder, limit, offset);
    }
}
