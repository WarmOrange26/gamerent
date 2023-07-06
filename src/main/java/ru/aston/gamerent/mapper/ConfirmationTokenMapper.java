package ru.aston.gamerent.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.entity.ConfirmationToken;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ConfirmationTokenMapper {

    ConfirmationResponseDto confirmationTokenToDto(ConfirmationToken confirmationToken);
}
