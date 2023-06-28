package ru.aston.gamerent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public String getUserById(long id) throws NoEntityException {
        User user = usersRepository.findById(id).orElseThrow(() -> new NoEntityException("User with id " + id + "was not found"));
        return user.toString();
    }
}
