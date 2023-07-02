package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.response.AccountResponse;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.dto.response.WalletResponse;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "wallets", expression = "java(walletsToWalletsResponse(wallets))")
    @Mapping(target = "accounts", expression = "java(accountToAccountsResponse(accounts))")
    UserResponse userToUserResponseDto(User user, List<Wallet> wallets, List<Account> accounts);

    List<WalletResponse> walletsToWalletsResponse(List<Wallet> wallets);

    List<AccountResponse> accountToAccountsResponse(List<Account> accounts);
}
