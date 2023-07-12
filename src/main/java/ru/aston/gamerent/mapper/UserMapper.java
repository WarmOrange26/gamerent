package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.SettingValueRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.dto.response.WalletResponseDto;
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
    UserResponseDto userToUserResponseDto(User user, List<Wallet> wallets, List<Account> accounts);

    List<WalletResponseDto> walletsToWalletsResponse(List<Wallet> wallets);

    List<AccountResponseDto> accountToAccountsResponse(List<Account> accounts);

    default User userRequestToUser(UserRequestDto userRequestDto, User userFromBD) {
        userFromBD.setUsername(userRequestDto.username());
        userFromBD.setEmail(userRequestDto.email());
        userFromBD.setPassword(userRequestDto.password());
        userFromBD.setFirstName(userRequestDto.firstName());
        userFromBD.setLastName(userRequestDto.lastName());
        userFromBD.setPhone(userRequestDto.phone());
        userFromBD.setBirthDate(userRequestDto.birthDate());
        userFromBD.setUpdateTime(LocalDateTime.now());

        Map<SettingsNamesEnum, Boolean> mapSettingsRequest = userRequestDto.settings().stream()
                .collect(Collectors.toMap(i -> SettingsNamesEnum.valueOf(i.settingName()), SettingValueRequestDto::value));

        List<SettingValue> settingsFromDB = userFromBD.getSettings();

        for (SettingValue settingValue : settingsFromDB) {
            SettingsNamesEnum settingName = settingValue.getSetting().getSettingName();
            if (mapSettingsRequest.containsKey(settingName)) {
                settingValue.setValue(mapSettingsRequest.get(settingName));
            }
        }

        return userFromBD;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isBlocked", ignore = true)
    @Mapping(target = "registrationTime", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "settings", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    User userRegistrationDtoToUser(RegistrationUserRequestDto registrationUserRequestDto);

    UserDto userToUserDto(User user);
}
