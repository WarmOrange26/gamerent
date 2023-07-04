package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponseDto(User user);
}
