package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.gamerent.model.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
