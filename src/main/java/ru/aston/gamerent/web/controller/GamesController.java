//package ru.aston.gamerent.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.aston.gamerent.service.AccountService;
//import ru.aston.gamerent.service.GameService;
//
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("api/v1/games")
//public class GamesController {
//    private final GameService gameService;
//    private final AccountService accountService;
//    @GetMapping("/{id}")
//    public String show(@PathVariable Long id, Model model){
//
//        model.addAttribute("gameResponse", gameService.getGameById(id));
//        model.addAttribute("numberOfAvailableAccounts", accountService.numberOfAvailableAccounts(id));
//
//        return "game";
//    }
//
//    @PostMapping("/{id}/postpone")
//    public String addToBucket(@PathVariable Long id, Model model){
//
//        return "development";
//    }
//}