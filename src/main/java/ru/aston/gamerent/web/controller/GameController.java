package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.service.GameService;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;


    @GetMapping("/games")
    public String findAll(Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        return "game-list";
    }

    @GetMapping("/game-create")
    public String createGameForm(GameRequest gameRequest) {
        return "game-create";
    }

    @PostMapping("/game-create")
    public String createGame(@ModelAttribute("gameRequest") @Valid GameRequest gameRequest) {
        gameService.saveGame(gameRequest);
        return "redirect:/games";
    }

    @GetMapping("/game-delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameService.deleteById(id);
        return "redirect:/games";
    }

    @GetMapping("/game-update/{id}")
    public String updateGameForm(@PathVariable("id") Long id, GameRequest gameRequest) {
        return "game-update";
    }

    @PostMapping("/game-update/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("gameRequest") @Valid GameRequest gameRequest) {
        gameService.updateGame(id, gameRequest);
        return "redirect:/games";
    }
}