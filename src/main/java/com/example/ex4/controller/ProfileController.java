package com.example.ex4.controller;

import com.example.ex4.model.User;
import com.example.ex4.service.UserService;
import com.example.ex4.session.UserSession;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    private UserService userService;

    @Resource
    private UserSession userSession;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public String showProfile(Model model) {
        Long userId = userSession.getUserId();
        User user = userService.findById(userId).orElse(null);

        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 Model model) {
        Long userId = userSession.getUserId();
        User user = userService.findById(userId).orElse(null);

        if (user == null || !passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Incorrect current password.");
            model.addAttribute("user", user);
            return "profile";
        }

        // עדכון הסיסמה המוצפנת
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);

        model.addAttribute("success", true);
        model.addAttribute("user", user);
        return "profile";
    }
}
