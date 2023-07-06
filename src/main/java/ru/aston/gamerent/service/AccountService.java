package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.OrderRequest;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponse;

import java.util.List;

public interface AccountService {

    void blockAccounts();

    List<ActiveAccountResponse> buyAccount(OrderRequest orderRequest);
}
