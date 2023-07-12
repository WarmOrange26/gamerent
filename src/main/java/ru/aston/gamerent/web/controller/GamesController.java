package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aston.gamerent.model.dto.request.GameRequestDto;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.service.DeveloperService;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.PlatformService;


@Controller
@RequiredArgsConstructor
@RequestMapping("games")
public class GamesController {
    public static final String GAME = "game";
    public static final String NUMBER_OF_AVAILABLE_ACCOUNTS = "numberOfAvailableAccounts";
    public static final String CATALOG = "catalog";
    public static final String GAMES = "games";
    public static final String GAME_REQUEST_DTO = "gameRequestDto";
    public static final String DEVELOPERS = "developers";
    public static final String PLATFORMS = "platforms";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String TOTAL_ITEMS = "totalItems";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String PAGE_SIZE = "pageSize";
    private final GameService gameService;
    private final AccountService accountService;
    private final DeveloperService developerService;
    private final PlatformService platformService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute(GAMES, gameService.getAllGames());

        return CATALOG;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute(GAME, gameService.getGameById(id));
        model.addAttribute(NUMBER_OF_AVAILABLE_ACCOUNTS, accountService.numberOfAvailableAccounts(id));

        return GAME;
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute(GAME_REQUEST_DTO,
                new GameRequestDto(null, null, null, null,
                        null, null));
        model.addAttribute(DEVELOPERS, developerService.findAllDevelopers());
        model.addAttribute(PLATFORMS, platformService.findAllPlatforms());
        return "searchForm.html";

    }

    @PostMapping("/search-results")
    public String showSearchResults(@ModelAttribute @Valid GameRequestDto gameRequestDto, BindingResult bindingResult, Model model,
                                    @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<GameResponseDto> pageGames = gameService.findGamesByFilter(gameRequestDto, paging);
        model.addAttribute(GAMES, pageGames.getContent());
        model.addAttribute(CURRENT_PAGE, pageGames.getNumber() + 1);
        model.addAttribute(TOTAL_ITEMS, pageGames.getTotalElements());
        model.addAttribute(TOTAL_PAGES, pageGames.getTotalPages());
        model.addAttribute(PAGE_SIZE, size);
        if (bindingResult.hasErrors()) {
            return "searchForm.html";
        } else
            return "searchResults.html";

    }
}