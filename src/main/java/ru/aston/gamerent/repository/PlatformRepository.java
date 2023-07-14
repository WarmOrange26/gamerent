package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.gamerent.model.entity.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    Platform findByName(String name);
}