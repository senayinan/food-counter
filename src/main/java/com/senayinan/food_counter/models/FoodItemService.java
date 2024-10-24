package com.senayinan.food_counter.models;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FoodItemService {
    private static final String USDA_API_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?query={query}&api_key={apiKey}";

    private final RestTemplate restTemplate;
    public FoodItemService(RestTemplate restTemplate)   {
        this.restTemplate = restTemplate;
    }
    public List<FoodItem> searchFoodItems(String query, String apiKey) {
        FoodItemSearchResponse response = restTemplate.getForObject(USDA_API_URL, FoodItemSearchResponse.class, query, apiKey);
        return response != null ? response.getFoods() : Collections.emptyList();
    }
}

