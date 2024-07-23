package com.javaMini2.Java.Mini2.Service;

import com.javaMini2.Java.Mini2.Entity.GenderResponse;
import com.javaMini2.Java.Mini2.Entity.NationalityResponse;
import com.javaMini2.Java.Mini2.Entity.ValidationResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ValidationService {


    private final WebClient webClient2;
    private final WebClient webClient3;

    public ValidationService(WebClient.Builder webClientBuilder) {

        this.webClient2 = webClientBuilder.baseUrl("https://api.nationalize.io").build();
        this.webClient3 = webClientBuilder.baseUrl("https://api.genderize.io").build();
    }

    public ValidationResponse validateUser(String name) {
        NationalityResponse nationalityResponse = webClient2.get()
                .uri(uriBuilder -> uriBuilder.queryParam("name", name).build())
                .retrieve()
                .bodyToMono(NationalityResponse.class)
                .block();
        System.out.println(nationalityResponse);

        GenderResponse genderResponse = webClient3.get()
                .uri(uriBuilder -> uriBuilder.queryParam("name", name).build())
                .retrieve()
                .bodyToMono(GenderResponse.class)
                .block();
        System.out.println(genderResponse);

        ValidationResponse validationResponse = new ValidationResponse();
        if (nationalityResponse != null) {
            validationResponse.setNationalities(nationalityResponse.getNationalities());
        }
//        System.out.println("hi i am here1");
        System.out.println(nationalityResponse.getNationalities());
        if (genderResponse != null) {
            validationResponse.setGender(genderResponse.getGender());
        }
        System.out.println(genderResponse.getGender());
//        System.out.println("hi i am here2");
        return validationResponse;

    }
}
