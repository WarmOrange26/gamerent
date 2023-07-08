package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.gamerent.model.entity.ConfirmationToken;
import java.util.Optional;
import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(UUID token);
}