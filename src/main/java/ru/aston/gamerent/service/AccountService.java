package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.OrderRequest;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponse;
import ru.aston.gamerent.model.entity.Account;
import java.util.List;

public interface AccountService {
    void blockAccounts();

    List<ActiveAccountResponse> buyAccount(OrderRequest orderRequest);

    Account getAccountById(long id);

    List<Account> findByGameId(Long gameId);

    int numberOfAvailableAccounts(Long gameId);
}
