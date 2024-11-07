package com.senayinan.food_counter.models;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService {
    private static final String USDA_API_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?query={query}&api_key={apiKey}";

    private final RestTemplate restTemplate;

    public FoodItemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to search food items using the query and API key
    public List<FoodItem> searchFoodItems(String query, String apiKey) {
        // Check if the API key is provided
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("USDA API key is not set in the environment variables.");
        }
        FoodItemSearchResponse response = restTemplate.getForObject(USDA_API_URL, FoodItemSearchResponse.class, query, apiKey);
        return response != null ? response.getFoods() : new ArrayList<>(); // Return empty list if none found
    }

    public List<Nutrient> fetchNutritionalInfoFromUSDA(Long fdcId, String apiKey) {
        List<Nutrient> nutrients = new ArrayList<>();
        try {
            String apiUrl = USDA_API_URL + fdcId + "?api_key=" + apiKey;
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

            List<Map<String, Object>> nutrientsData = (List<Map<String, Object>>) response.get("foodNutrients");
            // Traditional for-loop to populate the nutrients list
            for (Map<String, Object> nutrientData : nutrientsData) {
                String nutrientName = (String) nutrientData.get("nutrientName");
                Double amount = (Double) nutrientData.get("value");

                Nutrient nutrient = new Nutrient();
                nutrient.setNutrientName(nutrientName);
                nutrient.setAmount(amount);
                nutrients.add(nutrient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            return nutrients; // Return the populated list of nutrients

    }
}




