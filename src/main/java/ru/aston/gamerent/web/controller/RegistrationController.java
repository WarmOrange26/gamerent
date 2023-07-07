package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.service.EmailService;
import ru.aston.gamerent.service.UserService;
import java.util.Optional;
import java.util.UUID;

import static ru.aston.gamerent.model.dto.validation.ValidationConstants.UUID_PATTERN;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    public static final String PASSWORD_ERROR = "passwordError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String REGISTRATION_MESSAGE = "registrationMessage";
    private static final String VERIFY_ERROR = "verifyError";
    private static final String VERIFY_MESSAGE = "verifyMessage";
    private static final String INDEX_PAGE = "index";
    private static final String REGISTRATION_PAGE = "registration";
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping()
    public String newUser(@ModelAttribute("user") RegistrationUserRequestDto user) {
        return REGISTRATION_PAGE;
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid RegistrationUserRequestDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION_PAGE;
        }
        if (!user.password().equals(user.passwordConfirm())) {
            model.addAttribute(PASSWORD_ERROR, "Passwords does not match!");
            return REGISTRATION_PAGE;
        }
        Optional<ConfirmationResponseDto> confirmationToken = userService.saveUser(user);
        if (confirmationToken.isEmpty()) {
            model.addAttribute(EMAIL_ERROR, String.format("User with email %s already exists!", user.email()));
            return REGISTRATION_PAGE;
        }

        String message = emailService
                .sendRegistrationMail(user.username(), user.email(), user.password(), confirmationToken.get().token());
        model.addAttribute(REGISTRATION_MESSAGE, message);

        return INDEX_PAGE;
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") @Pattern(regexp = UUID_PATTERN) UUID token, Model model) {
        if (userService.confirmEmail(token)) {
            model.addAttribute(VERIFY_MESSAGE, "Email verified successfully! Now you can log in to the site.");
        } else {
            model.addAttribute(VERIFY_ERROR, "Couldn't verify email!");
        }

        return INDEX_PAGE;
    }
}
