package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.service.DeveloperService;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/developers")
public class DevelopersController {
    private final DeveloperService developerService;
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){

        model.addAttribute("developerResponse", developerService.findDeveloperById(id));

        return "developer";
    }
}
