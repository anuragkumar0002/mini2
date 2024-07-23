package com.javaMini2.Java.Mini2.Service;

import com.javaMini2.Java.Mini2.Entity.RandomUser;
import com.javaMini2.Java.Mini2.Entity.RandomUserResponse;
import com.javaMini2.Java.Mini2.Entity.User;
import com.javaMini2.Java.Mini2.Entity.ValidationResponse;
import com.javaMini2.Java.Mini2.Repository.UserRepository;
import com.javaMini2.Java.Mini2.Utility.SortingStrategy;
import com.javaMini2.Java.Mini2.Utility.SortingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private WebClient webClient1;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RandomUserService randomUserService;



    public List<User> createUsers(int size) {
//        System.out.println("inside create user");
        if (size < 1 || size > 5) {
            throw new IllegalArgumentException("Size must be between 1 and 5");
        }
        List<RandomUser> randomUsers = randomUserService.fetchRandomUsers(size);
        System.out.println(randomUsers);
        List<User> savedUsers = randomUsers.stream().map(randomUser -> {

            // Validate the user using the validation service
            ValidationResponse validationResponse =  validationService.validateUser(randomUser.getFirstName());

            System.out.println(validationResponse);
            // Create a new User object
            User user = new User();
            user.setName(randomUser.getFirstName() + " " + randomUser.getLastName());
            user.setAge(randomUser.getAge()); // Assuming you have age in RandomUser
            user.setGender(randomUser.getGender());
            user.setNationality(randomUser.getNationality());
            user.setVerificationStatus(verifyUser(user, validationResponse));
            user.setDateCreated(LocalDateTime.now());
            user.setDateModified(LocalDateTime.now());

            // Save the user to the database
            return userRepository.save(user);
        }).collect(Collectors.toList());
        System.out.println(savedUsers);
        return savedUsers;
    }
//        System.out.println(randomUsers);
//        List<User> users = fetchRandomUsers(size);
//


//        return randomUsers;

//        return users.stream().map(user -> {
//            ValidationResponse validationResponse = validationService.validateUser(user.getFirstName()).block();
//            user.setVerificationStatus(verifyUser(user, validationResponse));
//            user.setDateCreated(LocalDateTime.now());
//            user.setDateModified(LocalDateTime.now());
//            return userRepository.save(user);
//        }).collect(Collectors.toList());

//    }

    private List<User> fetchRandomUsers(int size) {
        return webClientBuilder.baseUrl("https://randomuser.me/api/")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("results", size).build())
                .retrieve()
                .bodyToMono(RandomUserResponse.class)
                .block()
                .getResults();
    }


    private String verifyUser(User user, ValidationResponse validationResponse) {
        boolean nationalityMatch = validationResponse.getNationalities().contains(user.getNationality());
        boolean genderMatch = validationResponse.getGender().equalsIgnoreCase(user.getGender());
        System.out.println(nationalityMatch);
        if (nationalityMatch && genderMatch) {
            return "VERIFIED";
        } else {
            return "TO_BE_VERIFIED";
        }
    }

    public List<User> getUsers(String sortType, String sortOrder, int limit, int offset) {
        List<User> allUsers = userRepository.findAll();

        System.out.println(allUsers);

        SortingStrategy sortingStrategy = SortingStrategyFactory.getSortingStrategy(sortType, sortOrder);
        List<User> sortedUsers = sortingStrategy.sort(allUsers);

        return sortedUsers.stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }
}