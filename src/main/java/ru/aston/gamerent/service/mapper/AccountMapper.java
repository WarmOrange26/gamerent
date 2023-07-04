package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponse;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.AccountsGames;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "games", source = "accountsGame")
    ActiveAccountResponse accountToActiveAccountResponse(Account account);

    default List<String> gameTitlesToString(List<AccountsGames> accountsGames) {
        return accountsGames.stream()
                .map(accountsGame -> accountsGame.getGame().getTitle())
                .toList();
    }
}
