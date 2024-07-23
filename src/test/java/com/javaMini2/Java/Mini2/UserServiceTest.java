package com.javaMini2.Java.Mini2;//package com.javaMini2.Java.Mini2.Service;

import com.javaMini2.Java.Mini2.Entity.RandomUser;
import com.javaMini2.Java.Mini2.Entity.User;
import com.javaMini2.Java.Mini2.Entity.ValidationResponse;
import com.javaMini2.Java.Mini2.Repository.UserRepository;
import com.javaMini2.Java.Mini2.Service.RandomUserService;
import com.javaMini2.Java.Mini2.Service.UserService;
import com.javaMini2.Java.Mini2.Service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidationService validationService;

    @Mock
    private RandomUserService randomUserService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createUsers_Success() {
        // Arrange
        int size = 3;
        RandomUser randomUser1 = new RandomUser("male", "John", "Doe", "US", 23);
        RandomUser randomUser2 = new RandomUser("female", "Jane", "Doe", "US",34);
        RandomUser randomUser3 = new RandomUser("male", "Jim", "Beam", "US",56);

        List<RandomUser> randomUsers = Arrays.asList(randomUser1, randomUser2, randomUser3);

        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setNationalities(Arrays.asList("US"));
        validationResponse.setGender("male");

        when(randomUserService.fetchRandomUsers(size)).thenReturn(randomUsers);
        when(validationService.validateUser(anyString())).thenReturn(validationResponse);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        List<User> createdUsers = userService.createUsers(size);

        // Assert
        assertEquals(3, createdUsers.size());
        assertEquals("John Doe", createdUsers.get(0).getName());
        assertEquals("Jane Doe", createdUsers.get(1).getName());
        assertEquals("Jim Beam", createdUsers.get(2).getName());

        verify(randomUserService, times(1)).fetchRandomUsers(size);
        verify(validationService, times(3)).validateUser(anyString());
        verify(userRepository, times(3)).save(any(User.class));
    }
}
