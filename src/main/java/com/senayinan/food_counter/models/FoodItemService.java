package com.senayinan.food_counter.models;

import com.senayinan.food_counter.data.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class FoodItemService {
    private static final String USDA_API_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?query={query}&api_key={apiKey}";

    private final RestTemplate restTemplate;

    private final FoodItemRepository foodItemRepository;

    // Constructor-based dependency injection for both RestTemplate and FoodItemRepository
    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, RestTemplate restTemplate) {
        this.foodItemRepository = foodItemRepository;
        this.restTemplate = restTemplate;
    }


    // Method to get food items by meal ID
    public List<FoodItem> getFoodItemsByMealId(Long mealId) {
        return foodItemRepository.findByMealId(mealId); // Fetch food items for the given meal ID
    }
    public Map<String, Object> fetchFoodData(String query, String apiKey) {

        // Ensure query and apiKey are properly encoded
        String encodedQuery = UriUtils.encode(query, StandardCharsets.UTF_8);
        String encodedApiKey = UriUtils.encode(apiKey, StandardCharsets.UTF_8);



        // Build the URL with query parameters
        String url = USDA_API_URL.replace("{query}", encodedQuery).replace("{apiKey}", apiKey);

        // Use ParameterizedTypeReference for type-safe handling of the Map<String, Object>
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // No additional request entity required
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        // Return the response body
        return responseEntity.getBody();
    }


    // Method to fetch all FoodItems from the database and convert Iterable to List
    public List<FoodItem> getAllFoodItems() {
        Iterable<FoodItem> foodItemIterable = foodItemRepository.findAll(); // This returns Iterable
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemIterable.forEach(foodItemList::add); // Convert Iterable to List
        return foodItemList;
    }

    // Method to get a FoodItem by its ID
    public Optional<FoodItem> getFoodItemById(Long foodItemId) {
        return foodItemRepository.findById(foodItemId);
    }

    // Method to save a FoodItem to the database
    public FoodItem saveFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    // Method to delete a FoodItem by its ID
    public void deleteFoodItemById(Long foodItemId) {
        foodItemRepository.deleteById(foodItemId);
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

    // Method to fetch nutritional info using fdcId
    public List<Nutrient> fetchNutritionalInfoFromUSDA(Long fdcId, String apiKey) {
        List<Nutrient> nutrients = new ArrayList<>();
        try {
            String apiUrl = "https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=" + apiKey;
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

            if (response != null && response.get("foodNutrients") instanceof List<?>) {
                List<?> nutrientsData = (List<?>) response.get("foodNutrients");

                for (Object item : nutrientsData) {
                    if (item instanceof Map<?, ?>) {
                        Map<?, ?> nutrientData = (Map<?, ?>) item;
                        String nutrientName = (String) nutrientData.get("nutrientName");
                        Double amount = (nutrientData.get("value") instanceof Number) ?
                                ((Number) nutrientData.get("value")).doubleValue() : null;

                        if (nutrientName != null && amount != null) {
                            Nutrient nutrient = new Nutrient(nutrientName, amount);
                            nutrients.add(nutrient);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();// This will output the full stack trace to help debug
        }
        return nutrients;
    }
}




