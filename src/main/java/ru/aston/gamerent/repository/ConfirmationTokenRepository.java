package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.gamerent.model.entity.ConfirmationToken;

import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(UUID token);
}