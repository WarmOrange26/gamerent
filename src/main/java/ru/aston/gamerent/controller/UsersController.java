package ru.aston.gamerent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.service.UsersService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id) throws NoEntityException {
        return usersService.getUserById(id);
    }
}
