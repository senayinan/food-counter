package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.models.FoodItem;
import com.senayinan.food_counter.models.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FoodItemSearchController {
    @Autowired
    private FoodItemService foodItemService;

    private final String apiKey = "YOUR_USDA_API_KEY"; // Replace with your actual API key

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<FoodItem> results = foodItemService.searchFoodItems(query, apiKey);
        model.addAttribute("query", query);
        model.addAttribute("results", results);
        return "searchResults";
    }
}
