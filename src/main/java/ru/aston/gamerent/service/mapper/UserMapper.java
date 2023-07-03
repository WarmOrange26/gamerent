package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.RegistrationUser;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponseDto(User user);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isBlocked", ignore = true)
    @Mapping(target = "registrationTime", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "settings", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    User userRegistrationDtoToUser(RegistrationUser registrationUser);
}
