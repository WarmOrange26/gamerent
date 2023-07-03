package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.request.SettingValueRequest;
import ru.aston.gamerent.model.dto.request.UserRequest;
import ru.aston.gamerent.model.dto.response.AccountResponse;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.dto.response.WalletResponse;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.SettingValue;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.enumeration.SettingsNamesEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "wallets", expression = "java(walletsToWalletsResponse(wallets))")
    @Mapping(target = "accounts", expression = "java(accountToAccountsResponse(accounts))")
    UserResponse userToUserResponseDto(User user, List<Wallet> wallets, List<Account> accounts);

    List<WalletResponse> walletsToWalletsResponse(List<Wallet> wallets);

    List<AccountResponse> accountToAccountsResponse(List<Account> accounts);

    default User userRequestToUser(UserRequest userRequest, User userFromBD) {
        userFromBD.setUsername(userRequest.username());
        userFromBD.setEmail(userRequest.email());
        userFromBD.setPassword(userRequest.password());
        userFromBD.setFirstName(userRequest.firstName());
        userFromBD.setLastName(userRequest.lastName());
        userFromBD.setPhone(userRequest.phone());
        userFromBD.setBirthDate(userRequest.birthDate());
        userFromBD.setUpdateTime(LocalDateTime.now());

        Map<SettingsNamesEnum, Boolean> mapSettingsRequest = userRequest.settings().stream()
                .collect(Collectors.toMap(i -> SettingsNamesEnum.valueOf(i.settingName()), SettingValueRequest::value));

        List<SettingValue> settingsFromDB = userFromBD.getSettings();

        for (SettingValue settingValue : settingsFromDB) {
            SettingsNamesEnum settingName = settingValue.getSetting().getSettingName();
            if (mapSettingsRequest.containsKey(settingName)) {
                settingValue.setValue(mapSettingsRequest.get(settingName));
            }
        }

        return userFromBD;
    }
}
