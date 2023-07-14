package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.service.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("games")
public class GamesController {
    private static final String GAME = "game";
    private static final String NUMBER_OF_AVAILABLE_ACCOUNTS = "numberOfAvailableAccounts";
    private static final String CATALOG = "catalog";
    private static final String GAMES = "games";
    private static final String REDIRECT_GAMES = "redirect:/games";
    private static final String GAME_UPDATE = "game-update";
    private static final String GAME_CREATE = "game-create";
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

    @GetMapping("/game-create")
    public String createGameForm(GameRequest gameRequest, Model model) {
        model.addAttribute("allGenres", gameService.findAllGenres());
        return GAME_CREATE;
    }

    @PostMapping("/game-create")
    public String createGame(@ModelAttribute("gameRequest") @Valid GameRequest gameRequest) {
        gameService.saveGame(gameRequest);
        return REDIRECT_GAMES;
    }

    @GetMapping("/game-delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameService.deleteById(id);
        return REDIRECT_GAMES;
    }

    @GetMapping("/game-update/{id}")
    public String updateGameForm(@PathVariable("id") Long id, GameRequest gameRequest, Model model) {
        model.addAttribute("allGenres", gameService.findAllGenres());
        return GAME_UPDATE;
    }

    @PostMapping("/game-update/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("gameRequest") @Valid GameRequest gameRequest) {
        gameService.updateGame(id, gameRequest);
        return REDIRECT_GAMES;
    }
}