package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.gamerent.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
