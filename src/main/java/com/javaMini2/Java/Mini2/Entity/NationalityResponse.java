package com.javaMini2.Java.Mini2.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class NationalityResponse {
    @JsonProperty("country")
    private List<Country> countries;

    @Data
    public static class Country {
        @JsonProperty("country_id")
        private String countryId;
        @JsonProperty("probability")
        private double probability;
    }

    public List<String> getNationalities() {
        return countries.stream()
                .map(Country::getCountryId)
                .collect(Collectors.toList());
    }
}
