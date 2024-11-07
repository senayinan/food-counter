package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @Autowired
    AuthenticationController authenticationController;

    // This method will run for all requests handled by HomeController

    // Add loggedIn globally to all views
    @ModelAttribute("loggedIn")
    public boolean isLoggedIn(HttpSession session) {
        User user = authenticationController.getUserFromSession(session);
        return user != null;
    }


    @GetMapping("/")
    public String index(Model model)   {

        model.addAttribute("message", "Welcome to the Home Page!");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model)    {

        model.addAttribute("title", "About Us");
        model.addAttribute("info", "This application helps you track your meals and nutrition.");

        return "about";
    }


    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("title", "Error Page");
        model.addAttribute("errorMessage", "An error has occurred. Please try again later.");

        return "error";
    }

    @GetMapping("/terms")
    public String terms(Model model) {
        model.addAttribute("title", "Terms of Service");
        model.addAttribute("terms", "By using this app, you agree to our terms and conditions.");

        return "terms";
    }


    @GetMapping("/privacy")
    public String privacy(Model model) {
        model.addAttribute("title", "Privacy Policy");
        model.addAttribute("privacy", "Your privacy is important to us. We only collect essential information.");

        return "privacy";
    }
}
