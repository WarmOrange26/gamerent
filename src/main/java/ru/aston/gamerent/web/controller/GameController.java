package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.mapper.GameMapper;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;


    @GetMapping("/games")
    public String findAll(Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        return "game-list";
    }

    @GetMapping("/game-create")
    public String createGameForm(Game game) {
        return "game-create";
    }

    @PostMapping("/game-create")
    public String createGame(@ModelAttribute("game") @Valid Game game) {
        gameService.saveGame(game);
        return "redirect:/games";
    }

    @GetMapping("/game-delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameService.deleteById(id);
        return "redirect:/games";
    }

    @GetMapping("/game-update/{id}")
    public String updateGameForm(@PathVariable("id") Long id, Model model) {
        GameResponse gameResponse = gameService.getGameById(id);
        model.addAttribute("game", gameResponse);
        return "game-update";
    }

    @PostMapping("/game-update")
    public String updateGame(@ModelAttribute("game") Game game) {
        gameService.saveGame(game);
        return "redirect:/games";
    }
}