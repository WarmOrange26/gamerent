package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.Developer;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByTitle(String title);
}
