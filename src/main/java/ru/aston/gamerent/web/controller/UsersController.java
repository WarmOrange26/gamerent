package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.service.UserService;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    public static final String UPDATE_MESSAGE = "User is update";
    public static final String PROFILE = "profile";
    public static final String USER = "user";
    public static final String USER_EDIT = "user-edit";
    private final UserService usersService;

    @GetMapping()
    public String getUser(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        UserResponseDto user = usersService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);

        return PROFILE;
    }

    @GetMapping("/edit")
    public String updateUserPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserResponseDto user = usersService.findUserByEmail(userDetails.getUsername());
        model.addAttribute(USER, user);

        return USER_EDIT;
    }

    @PostMapping("/edit")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute("user") @Valid UserRequestDto userRequestDto) {
        String email = userDetails.getUsername();
        usersService.updateUser(email, userRequestDto);

        return "redirect:/users/edit";
    }
}
