package ru.aston.gamerent.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.service.CartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public String showCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<GameResponseDto> games = cartService.findByUserEmail(userDetails.getUsername());
        model.addAttribute("games", games);

        return "/cart";
    }

    @PostMapping("/{gameId}/add")
    public String addToCart(@PathVariable Long gameId,
                            @AuthenticationPrincipal UserDetails userDetails,
                            HttpServletRequest request) {
        String redirectUrl = request.getHeader("referer");
        String username = userDetails.getUsername();

        cartService.addGameToCart(username, gameId);

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/{gameId}/delete")
    public String deleteFromCart(@PathVariable Long gameId,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        cartService.deleteGameFromCart(username, gameId);

        return "redirect:/cart";
    }
}