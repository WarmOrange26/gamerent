package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.model.dto.request.UserRequest;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService usersService;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestBody UserRequest userRequest) {
        usersService.updateUser(id, userRequest);
        return "User is update";
    }
}
