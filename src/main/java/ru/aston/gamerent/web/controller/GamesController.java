package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.service.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("games")
public class GamesController {
    public static final String GAME_RESPONSE = "gameResponse";
    public static final String NUMBER_OF_AVAILABLE_ACCOUNTS = "numberOfAvailableAccounts";
    public static final String GAME_PAGE = "game";
    public static final String DEVELOPMENT_PAGE = "development";
    public static final String GAMES = "games";
    private final GameService gameService;
    private final AccountService accountService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute(GAMES, gameService.getAllGames());

        return GAMES;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute(GAME_RESPONSE, gameService.getGameById(id));
        model.addAttribute(NUMBER_OF_AVAILABLE_ACCOUNTS, accountService.numberOfAvailableAccounts(id));

        return GAME_PAGE;
    }

    @PostMapping("/{id}/postpone")
    public String addToBucket(@PathVariable Long id, Model model){

        return DEVELOPMENT_PAGE;
    }
}