package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.data.DayRepository;
import com.senayinan.food_counter.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("days")
public class DayController {
    @Autowired
    private DayRepository dayRepository;
    @GetMapping
    public String displayAllDays(Model model)   {
        model.addAttribute("title", "All Days");
        model.addAttribute("days", dayRepository.findAll());
        return  "days/index";
    }
    @GetMapping("create")
    public String renderCreateDayForm(Model model)  {
        model.addAttribute("title", "Create Day");
        model.addAttribute(new Day());
        return "days/create";
    }
   @PostMapping("create")
    public String processCreateDayForm(@ModelAttribute @Valid Day day,
                                       Errors errors, Model model) {
        if(errors.hasErrors())  {
            model.addAttribute("title", "Create Day");
            return "days/create";
        }
        dayRepository.save(day);
        return "redirect:/days";
   }
    @GetMapping("edit/{dayId}")
    public String displayEditDayForm(Model model, @PathVariable int dayId) {
        Optional<Day> optionalDay = dayRepository.findById(dayId);
        if (optionalDay.isPresent()) {
            Day dayToEdit = optionalDay.get();

            model.addAttribute("title", "Edit Day: " + dayToEdit.getDate());
            model.addAttribute("day", dayToEdit);
            return "days/edit";
        } else {
            model.addAttribute("title", "Day not found!");
            return "redirect:/days";
        }
    }
    @PostMapping("edit/{id}")
    public String processEditDayForm(@PathVariable int id, @ModelAttribute @Valid Day day,
                                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Day");
            return "days/edit";
        }
        Optional<Day> optionalDay = dayRepository.findById(id);
        if(optionalDay.isPresent()) {
            Day existingDay = optionalDay.get();
            existingDay.setDate(day.getDate());
            dayRepository.save(existingDay);
        }
        return "redirect:/days";

    }
    @GetMapping("delete")
    public String displayDeleteDayForm(Model model)    {
        model.addAttribute("title", "Delete Days");
        model.addAttribute("days", dayRepository.findAll());
        return "days/delete";
    }
    @PostMapping("delete")
    public String processDeleteDayForm(@RequestParam(required = false) int[] dayIds, Model model)  {
        if(dayIds != null) {
            for(int dayId : dayIds)   {
                dayRepository.deleteById(dayId);
            }
        }   else {
            model.addAttribute("title", "Delete Days");
            model.addAttribute("days", dayRepository.findAll());
            model.addAttribute("error", "No days selected for deletion.");
            return  "days/delete";
        }
        return  "redirect:/days";
    }
}
