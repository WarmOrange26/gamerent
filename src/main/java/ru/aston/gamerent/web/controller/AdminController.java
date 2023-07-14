package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.gamerent.model.dto.request.AccountRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseInfoDto;
import ru.aston.gamerent.service.AccountService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private static final String ACCOUNTS_PAGE = "accounts";
    private static final String INDEX_PAGE = "index";

    private final AccountService accountService;

    @GetMapping("/accounts")
    public String newAccount(@ModelAttribute("account") AccountRequestDto account) {
        return ACCOUNTS_PAGE;
    }

    @PostMapping("/accounts")
    public String addAccount(@ModelAttribute("account") @Valid AccountRequestDto account,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ACCOUNTS_PAGE;
        }
        AccountResponseInfoDto accountResponse = accountService.saveAccount(account);
        if (accountResponse != null) {
            model.addAttribute("success", accountResponse.toString());
            return ACCOUNTS_PAGE;
        }
        return INDEX_PAGE;
    }
}
