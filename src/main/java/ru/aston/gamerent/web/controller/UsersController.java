package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UsersController {
    public static final String UPDATE_MESSAGE = "User is update";
    private final UserService usersService;

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @RequestBody UserRequestDto userRequestDto) {
        usersService.updateUser(id, userRequestDto);

        return UPDATE_MESSAGE;
    }
}
