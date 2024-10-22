package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.data.MealRepository;
import com.senayinan.food_counter.models.FoodItem;
import com.senayinan.food_counter.models.Meal;
import com.senayinan.food_counter.models.MealType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("meals")
public class MealController {
    @Autowired
    private MealRepository mealRepository;
    @GetMapping
    public String displayAllMeals(Model model) {
        model.addAttribute("title", "All Meals");
        model.addAttribute("meals", mealRepository.findAll());
        return "meals/index";
    }

    @GetMapping("create")
    public String displayCreateMealForm(Model model) {
        model.addAttribute("title", "Create Meal");
        model.addAttribute("meal", new Meal());
        model.addAttribute("mealTypes", MealType.values());

        return "meals/create";
    }

    @PostMapping("create")
    public String processCreateMealForm(@ModelAttribute @Valid Meal newMeal,
                                        Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Meal");
            model.addAttribute("mealTypes", MealType.values());
            return "meals/create";
        }
        mealRepository.save(newMeal);
        return "redirect:/meals";
    }
    @GetMapping("delete")
    public String displayDeleteMealForm(Model model)    {
        model.addAttribute("title", "Delete Meals");
        model.addAttribute("meals", mealRepository.findAll());
        return "meals/delete";
    }
    @PostMapping("delete")
    public String processDeleteMealForm(@RequestParam(required = false) int[] mealIds) {
        if (mealIds != null) {
            for (int mealId : mealIds) {
                mealRepository.deleteById(mealId);
            }
        }
            return "redirect:/meals";
    }
    @GetMapping("edit/{mealId}")
    public String displayEditMealForm(Model model, @PathVariable int mealId) {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (optionalMeal.isPresent()) {
            Meal mealToEdit = optionalMeal.get();
            String title = "Edit Meal " + mealToEdit.getMealType() + " (ID=" + mealToEdit.getId() + ")";
            model.addAttribute("meal", mealToEdit);
            model.addAttribute("title", title);
            model.addAttribute("mealTypes", MealType.values());
            return "meals/edit";
        } else {
            return "redirect:/meals";
        }
    }
    @PostMapping("edit/{mealId}")
    public String processEditMealForm(@PathVariable int mealId,
                                      @ModelAttribute @Valid Meal meal,
                                      Errors errors, Model model) {
        if(errors.hasErrors())  {
            model.addAttribute("title", "Edit Meal");
            model.addAttribute("mealTypes", MealType.values());
            return  "meals/edit";
        }
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (optionalMeal.isPresent()) {
            Meal mealToEdit = optionalMeal.get();
            mealToEdit.setMealType(meal.getMealType());
            mealToEdit.setFoodItems(meal.getFoodItems());
            mealRepository.save(mealToEdit);
        }
        return "redirect:/meals";
    }
    @GetMapping("view/{mealId}")
    public String viewMeal(Model model, @PathVariable int mealId)   {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if(optionalMeal.isPresent())    {
            Meal meal = optionalMeal.get();
            model.addAttribute("meal", meal);
            model.addAttribute("title", "View Meal " + meal.getMealType() + " (ID=" + meal.getId() + ")");
            return  "meals/view";
        }   else {
            return  "redirect:/meals";
        }
    }
}
