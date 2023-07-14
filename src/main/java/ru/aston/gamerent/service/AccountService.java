package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.AccountRequestDto;
import ru.aston.gamerent.model.dto.request.OrderRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseInfoDto;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponseDto;
import ru.aston.gamerent.model.entity.Account;
import java.util.List;

public interface AccountService {
    void blockAccounts();

    List<ActiveAccountResponseDto> buyAccount(OrderRequestDto orderRequestDto);

    Account getAccountById(Long id);

    List<Account> findByGameId(Long gameId);

    int numberOfAvailableAccounts(Long gameId);

    AccountResponseInfoDto saveAccount(AccountRequestDto accountRequestDto);
}
