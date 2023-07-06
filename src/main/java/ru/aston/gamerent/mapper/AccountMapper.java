package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.AccountsGames;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "games", source = "accountsGame")
    ActiveAccountResponseDto accountToActiveAccountResponse(Account account);

    default List<String> gameTitlesToString(List<AccountsGames> accountsGames) {
        return accountsGames.stream()
                .map(accountsGame -> accountsGame.getGame().getTitle())
                .toList();
    }
}
