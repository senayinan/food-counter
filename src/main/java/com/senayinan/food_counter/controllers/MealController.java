package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.models.Meal;
import com.senayinan.food_counter.models.MealType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("meals")
public class MealController {
    private static List<Meal> meals = new ArrayList<>();

    @GetMapping
    public String displayAllMeals(Model model) {
        model.addAttribute("meals", meals);
        return "meals/index";
    }

    @GetMapping("create")
    public String renderCreateMealForm() {

        return "meals/create";
    }

    @PostMapping("create")
    public String createMeal(@RequestParam MealType mealType) {
        Meal newMeal = new Meal(mealType);
        meals.add(newMeal);
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Meal meal = meals.get(id);
        model.addAttribute("meal", meal);
        return "meals/edit";
    }

    @PostMapping("edit/{id}")
    public String editMeal(@PathVariable int id, @RequestParam MealType mealType,
                           @RequestParam double calories, @RequestParam double carbs) {
        Meal meal = meals.get(id);
        meal.setMealType(mealType);
        return "redirect:/meals";
    }

    @GetMapping("delete/{id}")
    public String showDeleteForm(@PathVariable int id, Model model) {
        Meal meal = meals.get(id);
        model.addAttribute("meal", meal);
        return "meals/delete";
    }

    public String deleteMeal(@PathVariable int id)  {
        meals.remove(id);
        return "redirect:/meals";
    }
}
