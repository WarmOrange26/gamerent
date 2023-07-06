package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.service.EmailService;
import ru.aston.gamerent.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    public static final String PASSWORD_ERROR = "passwordError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String REGISTRATION_MESSAGE = "registrationMessage";
    private static final String INDEX_PAGE = "index";
    private static final String REGISTRATION_PAGE = "registration";
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") RegistrationUserRequestDto user) {
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid RegistrationUserRequestDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION_PAGE;
        }
        if (!user.password().equals(user.passwordConfirm())) {
            model.addAttribute(PASSWORD_ERROR, "Passwords does not match!");
            return REGISTRATION_PAGE;
        }
        if (userService.saveUser(user).id() == null) {
            model.addAttribute(EMAIL_ERROR, String.format("User with email %s already exists!", user.email()));
            return REGISTRATION_PAGE;
        }
        String message = emailService.sendRegistrationMail(user.username(), user.email(), user.password());
        model.addAttribute(REGISTRATION_MESSAGE, message);

        return INDEX_PAGE;
    }
}
