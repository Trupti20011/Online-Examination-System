package com.example.online_exam_system.Controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Correct import for Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.online_exam_system.Model.User;
import com.example.online_exam_system.Services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Ensure password is encoded before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login"; // Redirect to login if principal is null
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") User updatedUser, Principal principal) {
        if (principal != null) {
            User existingUser = userService.findByUsername(principal.getName());
            existingUser.setUsername(updatedUser.getUsername());
            // Update other fields as necessary
            userService.save(existingUser); // Assuming userService.save(user) updates the existing user
            return "redirect:/profile";
        }
        return "redirect:/login"; // Redirect to login if principal is null
    }

    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute("password") String newPassword, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user); // Assuming userService.save(user) updates the user's password
            return "redirect:/profile";
        }
        return "redirect:/login"; // Redirect to login if principal is null
    }
}
