package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.OrderRequest;

public interface AccountService {

    void blockAccounts();

    Boolean buyAccount(OrderRequest orderRequest);
}
