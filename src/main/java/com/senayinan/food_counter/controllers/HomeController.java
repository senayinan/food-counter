package com.senayinan.food_counter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model)   {
        model.addAttribute("message", "Welcome to the Home Page!");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model)    {
        model.addAttribute("info", "This application helps you track your meals and nutrition.");
        return "about";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
