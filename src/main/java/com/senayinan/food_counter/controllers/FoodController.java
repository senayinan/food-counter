package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.data.FoodItemRepository;
import com.senayinan.food_counter.data.FoodRepository;
import com.senayinan.food_counter.models.Food;
import com.senayinan.food_counter.models.FoodType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public String displayAllFoods(Model model) {
        model.addAttribute("title", "All Food Items");
        model.addAttribute("foods", foodRepository.findAll());
        return "food/index";
    }

    @GetMapping("create")
    public String renderCreateFoodForm(Model model) {
        model.addAttribute("title", "Create Food");
        model.addAttribute("food", new Food());
        model.addAttribute("foodTypes", FoodType.values());
        return "food/create";
    }

    @PostMapping("create")
    public String processCreateFoodForm(@ModelAttribute @Valid Food food, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Food");
            model.addAttribute("foodTypes", FoodType.values());
            return "food/create";
        }
        foodRepository.save(food);
        return "redirect:/food";
    }

    @GetMapping("edit/{foodId}")
    public String displayEditFoodForm(Model model, @PathVariable int foodId) {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isPresent()) {
            Food foodToEdit = optionalFood.get();
            model.addAttribute("title", "Edit Food Item: " + foodToEdit.getName());
            model.addAttribute("food", foodToEdit);
            model.addAttribute("foodTypes", FoodType.values());
            return "food/edit";
        } else {
            return "redirect:/food";
        }
    }

    @PostMapping("edit/{id}")
    public String processEditFoodForm(@PathVariable int id, @ModelAttribute @Valid Food food,
                                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Food");
            model.addAttribute("foodTypes", FoodType.values());
            return "food/edit";
        }
        foodRepository.save(food);
        return "redirect:/food";

    }
    @PostMapping("delete")

    public String processDeleteFoodForm(@RequestParam(required = false) int[] foodIds) {
        if (foodIds != null) {
            for (int foodId : foodIds) {
                foodRepository.deleteById(foodId);
            }
        }
        return "redirect:/food";
    }
    @GetMapping("view/{id}")
    public String viewFood(Model model, @PathVariable int foodId)   {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isPresent())    {
            Food food = optionalFood.get();
            model.addAttribute("food", food);
            model.addAttribute("title", "View Food: " + food.getName());
            return  "food/view";
        }   else {
            return  "redirect:/food";
        }
    }
}
