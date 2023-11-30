package com.gfg.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfg.app.model.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(User user, HttpServletResponse response) {
        // For simplicity, we'll just set a cookie with the username
        Cookie cookie = new Cookie("usernameCookie", user.getUsername());
        response.addCookie(cookie);
        return "redirect:/user/welcome";
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcomeUser(@CookieValue(value = "usernameCookie", defaultValue = "Guest") String username) {
        return "Welcome, " + username + "!";
    }
}

