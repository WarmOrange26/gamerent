package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.service.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("games")
public class GamesController {
    public static final String GAME = "game";
    public static final String NUMBER_OF_AVAILABLE_ACCOUNTS = "numberOfAvailableAccounts";
    public static final String CATALOG = "catalog";
    public static final String GAMES = "games";
    private final GameService gameService;
    private final AccountService accountService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute(GAMES, gameService.getAllGames());

        return CATALOG;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute(GAME, gameService.getGameById(id));
        model.addAttribute(NUMBER_OF_AVAILABLE_ACCOUNTS, accountService.numberOfAvailableAccounts(id));

        return GAME;
    }
}