package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
