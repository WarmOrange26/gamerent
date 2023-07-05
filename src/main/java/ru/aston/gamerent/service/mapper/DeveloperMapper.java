package ru.aston.gamerent.service.mapper;
import jakarta.annotation.security.DeclareRoles;
import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.DeveloperResponse;
import ru.aston.gamerent.model.entity.Developer;
@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    DeveloperResponse developerToDeveloperResponse(Developer developer);
}
