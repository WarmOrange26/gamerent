package ru.aston.gamerent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthenticationController {
    public static final String LOGIN = "login";

    @GetMapping("login")
    public String authenticate() {
        return LOGIN;
    }
}
