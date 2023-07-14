package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.request.AccountRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseInfoDto;
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

    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "ordersAccounts", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expirationTime", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "accountsGame", ignore = true)
    Account accountRequestDtoToAccount(AccountRequestDto accountRequestDto);

    @Mapping(target = "platformName", source = "platform.name")
    AccountResponseInfoDto accountToAccountResponseInfoDto(Account account);
}
