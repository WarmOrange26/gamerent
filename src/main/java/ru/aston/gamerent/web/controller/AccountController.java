package ru.aston.gamerent.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.model.dto.request.OrderRequest;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponse;
import ru.aston.gamerent.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/vi/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/buying")
    public List<ActiveAccountResponse> buyAccounts(@RequestBody OrderRequest orderRequest) {
        return accountService.buyAccount(orderRequest);
    }
}