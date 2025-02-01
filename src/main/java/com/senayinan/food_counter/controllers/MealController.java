package com.senayinan.food_counter.controllers;
import com.senayinan.food_counter.data.MealRepository;
import com.senayinan.food_counter.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/meals")
public class MealController {
    @Autowired
    private MealService mealService;

    @Autowired
    private FoodItemService foodItemService;


    @GetMapping
    public String displayAllMeals(Model model) {
        List<Meal> meals = mealService.getAllMeals();
        model.addAttribute("title", "All Meals");
        model.addAttribute("meals", mealService.getAllMeals());
        return "meals/index";
    }



    @GetMapping("create")
    public String displayCreateMealForm(Model model) {
        Meal meal = new Meal();
        meal.getFoodItems().add(new FoodItem()); // Add at least one FoodItem to the Meal
        model.addAttribute("title", "Create Meal");
        model.addAttribute("meal", meal);
        model.addAttribute("mealTypes", MealType.values());
        model.addAttribute("foodTypes", FoodType.values());
        model.addAttribute("servingSizes", ServingSize.values());
        return "meals/create";
    }

    @PostMapping("create")
    public String processCreateMealForm(@ModelAttribute @Valid Meal newMeal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Create Meal");
            model.addAttribute("mealTypes", MealType.values());
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "meals/create";
        }

        if (newMeal.getDate() == null) {
            newMeal.setDate(LocalDate.now());  // Set current date if none is provided
        }

        mealService.saveMeal(newMeal); // Save the meal, including date
        return "redirect:/meals";
    }





    /*
    @PostMapping("create")
    public String processCreateMealForm(@ModelAttribute @Valid Meal newMeal, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Meal");
            model.addAttribute("mealTypes", MealType.values());
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "meals/create";
        }

        if (newMeal.getDate() == null) {
            newMeal.setDate(LocalDate.now());  // Set current date if none is provided
        }


        mealService.saveMeal(newMeal); // Save Meal, including date
        return "redirect:/meals";
    }

     */

    /*
    @GetMapping("create")
    public String displayCreateMealForm(Model model) {
        Meal meal = new Meal();
        meal.getFoodItems().add(new FoodItem()); // Add at least one FoodItem to the Meal
        model.addAttribute("title", "Create Meal");
        model.addAttribute("meal", meal);
        model.addAttribute("mealTypes", MealType.values());
        model.addAttribute("foodTypes", FoodType.values());
        model.addAttribute("servingSizes", ServingSize.values());
        //model.addAttribute("days", dayRepository.findAll()); // Fetch all days and add to the model

        return "meals/create";
    }


    @PostMapping("create")
    public String processCreateMealForm(@ModelAttribute @Valid Meal newMeal,
                                        Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Meal");
            model.addAttribute("mealTypes", MealType.values());
            model.addAttribute("foodTypes", FoodType.values());
            model.addAttribute("servingSizes", ServingSize.values());
            return "meals/create";
        }

        mealService.saveMeal(newMeal);
        return "redirect:/meals";
    }

     */


    @GetMapping("delete")
    public String displayDeleteMealForm(Model model)    {
        model.addAttribute("title", "Delete Meals");
        model.addAttribute("meals", mealService.getAllMeals());
        return "meals/delete";
    }
    @PostMapping("delete")
    public String processDeleteMealForm(@RequestParam(required = false) Long[] mealIds) {
        if (mealIds != null) {
            for (Long mealId : mealIds) {
                mealService.deleteMealById(mealId);
            }
        }
            return "redirect:/meals";
    }
    @GetMapping("edit/{mealId}")
    public String displayEditMealForm(Model model, @PathVariable Long mealId) {
        Optional<Meal> optionalMeal = mealService.getMealById(mealId);
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
    public String processEditMealForm(@PathVariable Long mealId,
                                      @ModelAttribute @Valid Meal meal,
                                      Errors errors, Model model) {
        if(errors.hasErrors())  {
            model.addAttribute("title", "Edit Meal");
            model.addAttribute("meal", meal);
            model.addAttribute("mealTypes", MealType.values());
            return  "meals/edit";
        }
        Optional<Meal> optionalMeal = mealService.getMealById(mealId);
        if (optionalMeal.isPresent()) {
            Meal mealToEdit = optionalMeal.get();
            mealToEdit.setMealType(meal.getMealType());
            mealToEdit.setFoodItems(meal.getFoodItems());
            mealService.saveMeal(mealToEdit);
        }
        return "redirect:/meals";
    }
    @GetMapping("view/{mealId}")
    public String viewMeal(Model model, @PathVariable Long mealId)   {
        Optional<Meal> optionalMeal = mealService.getMealById(mealId);

        if(optionalMeal.isPresent())    {
            Meal meal = optionalMeal.get();
            model.addAttribute("meal", meal);
            model.addAttribute("title", "View Meal " + meal.getMealType() + " (ID=" + meal.getId() + ")");
            return  "meals/view";
        }   else {
            return  "redirect:/meals";
        }
    }

    @GetMapping("/meals")
    public String getMealsByDate(@RequestParam("date") LocalDate date, Model model) {
        List<Meal> meals = mealService.getMealsByDate(date);
        model.addAttribute("meals", meals);
        return "meals/index"; // Return the meals list view
    }
}
