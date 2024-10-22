package com.senayinan.food_counter.controllers;

import com.senayinan.food_counter.data.UserRepository;
import com.senayinan.food_counter.models.Meal;
import com.senayinan.food_counter.models.MealType;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("create")
    public String displayCreateUserForm(Model model)    {
        model.addAttribute("title", "Create User");
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @PostMapping("create")
    public String processCreateUserForm(@ModelAttribute @Valid User newUser,
                                        Errors errors, Model model) {
        if(errors.hasErrors())  {
            model.addAttribute("title", "Create User");
            return "users/create";
        }

        userRepository.save(newUser);
        return "return:/users";
    }
    @GetMapping("edit/{userId}")
    public String displayEditUserForm(Model model, @PathVariable int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User userToEdit = optionalUser.get();
            model.addAttribute("user", userToEdit);
            model.addAttribute("title", "Edit User: " + userToEdit.getUsername());
            model.addAttribute("user", userToEdit);
            return "users/edit";
        } else {
            return "redirect:/users";
        }
    }
    @PostMapping("edit/{userId}")
    public String processEditUserForm(@PathVariable int userId,
                                      @ModelAttribute @Valid User user,
                                      Errors errors, Model model) {
        if(errors.hasErrors())  {
            model.addAttribute("title", "Edit User");
            return  "users/edit";
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User userToEdit = optionalUser.get();
            userToEdit.setUsername(user.getUsername());
            userToEdit.setEmail(user.getEmail());

            if (user.getPwHash() != null && !user.getPwHash().isEmpty()) {
                userToEdit.setPwHash(user.getPwHash());
            }
            userRepository.save(userToEdit);
        }
        return "redirect:/users";
    }


}
