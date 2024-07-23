package com.javaMini2.Java.Mini2.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaMini2.Java.Mini2.Entity.RandomUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandomUserService {

    private final WebClient webClient;

    public RandomUserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://randomuser.me/api/").build();
    }

    public List<RandomUser> fetchRandomUsers(int size) {
        return this.webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseResponse)
                .flatMapMany(Flux::fromIterable)
                .collectList()
                .block();
    }


    private List<RandomUser> parseResponse(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RandomUser> randomUsers = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody); //parse the json data
            JsonNode resultsNode = jsonNode.get("results");
//            System.out.println("------------------------jj");
//            System.out.println(resultsNode);
            if (resultsNode != null && resultsNode.isArray()) {
                for (JsonNode userNode : resultsNode) {
                    String gender = userNode.get("gender").asText();
                    int age = userNode.get("dob").get("age").asInt();
                    String firstName = userNode.get("name").get("first").asText();
                    String lastName = userNode.get("name").get("last").asText();
                    String nationality = userNode.get("nat").asText();
                    randomUsers.add(new RandomUser(gender, firstName, lastName, nationality, age));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomUsers;
    }

//    private List<RandomUser> parseResponse(String responseBody) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<RandomUser> randomUsers = new ArrayList<>();
//        try {
//            JsonNode jsonNode = objectMapper.readTree(responseBody);
//            JsonNode resultsNode = jsonNode.get("results");
//            if (resultsNode != null && resultsNode.isArray()) {
//                for (JsonNode userNode : resultsNode) {
//                    String gender = userNode.get("gender").asText();
//                    String firstName = userNode.get("name").get("first").asText();
//                    String lastName = userNode.get("name").get("last").asText();
//                    String nationality = userNode.get("nat").asText();
//                    randomUsers.add(new RandomUser(gender, firstName, lastName, nationality));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return randomUsers;
//    }
}
