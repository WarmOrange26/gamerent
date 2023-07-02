package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.OrderRequest;

import java.util.Map;

public interface AccountService {

    void blockAccounts();

    Map<String, String> buyAccount(OrderRequest orderRequest);
}
