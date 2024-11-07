package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.data.FoodItemRepository;
import com.senayinan.food_counter.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/foodItems")
public class FoodItemController {
    @Value("${usda.api.key}")// Inject the USDA API key from configuration
    private String apiKey;
    @Autowired
    private FoodItemRepository foodItemRepository;

    @GetMapping
    public String displayAllFoodItems(Model model) {
        model.addAttribute("title", "All Food Items");
        model.addAttribute("foodItems", foodItemRepository.findAll());
        return "foodItems/index";
    }

    @GetMapping("/create")
    public String renderCreateFoodItemForm(Model model) {
        model.addAttribute("title", "Create Food Item");
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("foodTypes", FoodType.values());
        model.addAttribute("servingSizes", ServingSize.values());
        return "foodItems/create";
    }

    @PostMapping("/create")
    public String processCreateFoodItem(@ModelAttribute @Valid FoodItem newFoodItem,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Food Item");
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "foodItems/create";
        }
        // Fetch nutrient data from USDA API using fdcId before saving
        List<Nutrient> nutrients = fetchNutritionalInfoFromUSDA(newFoodItem.getFdcId());

        newFoodItem.setFoodItemNutrients(nutrients);
        foodItemRepository.save(newFoodItem);
        return "redirect:/foodItems";
    }

    @GetMapping("/delete")
    public String displayDeleteFoodItemForm(Model model) {
        model.addAttribute("title", "Delete Food Items");
        model.addAttribute("foodItems", foodItemRepository.findAll());
        return "foodItems/delete";
    }

    @PostMapping("/delete")
    public String processDeleteFoodItemForm(@RequestParam(required = false) int[] foodItemIds) {
        if (foodItemIds != null) {
            for (int foodItemId : foodItemIds) {
                foodItemRepository.deleteById(foodItemId);
            }
        }
        return "redirect:/foodItemId";
    }


    @GetMapping("edit/{foodItemId}")
    public String displayEditFoodItemForm(Model model, @PathVariable int foodItemId) {
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(foodItemId);
        if (optionalFoodItem.isPresent()) {
            FoodItem foodItemToEdit = optionalFoodItem.get();
            model.addAttribute("title", "Edit Food Item");
            model.addAttribute("foodItem", foodItemToEdit);
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "foodItems/edit";
        } else {
            model.addAttribute("error", "food item not found");
            return "foodItems/error";//I'll need error.html template in foodItems
        }
    }

    @PostMapping("/edit/{foodItemId}")
    public String processEditFoodItemForm(@PathVariable int foodItemId, @ModelAttribute @Valid FoodItem foodItem,
                                          Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Food Item");
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "foodItems/edit";
        }
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(foodItemId);
        if (optionalFoodItem.isPresent()) {
            FoodItem foodItemToEdit = optionalFoodItem.get();

            foodItemToEdit.setName(foodItem.getName());
            foodItemToEdit.setQuantity(foodItem.getQuantity());
            foodItemToEdit.setFoodType(foodItem.getFoodType());
            foodItemToEdit.setServingSize(foodItem.getServingSize());

            // Re-fetch nutrient data if fdcId has changed
            if (!foodItemToEdit.getFdcId().equals(foodItem.getFdcId())) {
                foodItemToEdit.setFdcId(foodItem.getFdcId());
                List<Nutrient> nutrients = fetchNutritionalInfoFromUSDA(foodItem.getFdcId());
                foodItemToEdit.setFoodItemNutrients(nutrients);
            }

            foodItemRepository.save(foodItemToEdit);
        }
        return "redirect:/foodItems";
    }

    @GetMapping("/view/{foodItemId}")
    public String viewFoodItem(Model model, @PathVariable int foodItemId) {
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(foodItemId);
        if (optionalFoodItem.isPresent()) {
            FoodItem foodItem = optionalFoodItem.get();
            model.addAttribute("foodItem", foodItem);
            return "foodItems/view";
        } else {
            return "redirect:/error";
        }
    }


    private List<Nutrient> fetchNutritionalInfoFromUSDA(Long fdcId) {
        try {
            String apiUrl = "https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=" + apiKey;
            RestTemplate restTemplate = new RestTemplate();

            USDAFoodItemResponse response = restTemplate.getForObject(apiUrl, USDAFoodItemResponse.class);
            List<Nutrient> nutrientList = new ArrayList<>();

            if (response != null && response.getFoodNutrients() != null) {
                for (USDAFoodNutrient usdaFoodNutrient : response.getFoodNutrients()) {
                    Nutrient nutrient = new Nutrient();
                    nutrient.setNutrientName(usdaFoodNutrient.getNutrientName());
                    nutrient.setAmount(usdaFoodNutrient.getValue());
                    nutrientList.add(nutrient);
                }
            }


            return nutrientList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }
}


