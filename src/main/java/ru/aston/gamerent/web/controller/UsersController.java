package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.service.UserService;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UsersController {
    public static final String UPDATE_MESSAGE = "User is update";
    public static final String PROFILE = "profile";
    public static final String USER = "user";
    private final UserService usersService;

    @GetMapping()
    public String getUser(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        UserResponseDto user = usersService.findUserByEmail(userDetails.getUsername());
        model.addAttribute(USER, user);

        return PROFILE;
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @RequestBody UserRequestDto userRequestDto) {
        usersService.updateUser(id, userRequestDto);

        return UPDATE_MESSAGE;
    }
}
