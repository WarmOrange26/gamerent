package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.PostponedGameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("games")
public class GamesController {
    public static final String GAME = "game";
    public static final String NUMBER_OF_AVAILABLE_ACCOUNTS = "numberOfAvailableAccounts";
    public static final String DEVELOPMENT = "development";
    public static final String GAMES = "games";
    private final GameService gameService;
    private final AccountService accountService;
    private final PostponedGameService postponedGameService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute(GAMES, gameService.getAllGames());

        return GAMES;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute(GAME, gameService.getGameById(id));
        model.addAttribute(NUMBER_OF_AVAILABLE_ACCOUNTS, accountService.numberOfAvailableAccounts(id));

        return GAME;
    }

    @PostMapping("/{id}/add")
    public String addToCart(@PathVariable Long id, Model model){
        Long userId = 1L;
        postponedGameService.postponeGame(userId, id);
        model.addAttribute("gameResponse", gameService.getGameById(id));
        model.addAttribute("numberOfAvailableAccounts", accountService.numberOfAvailableAccounts(id));
        //model.addAttribute("gameIsAddedToCart", postponedGameService.postponeGame(userId, id));
        return GAME;
    }
}