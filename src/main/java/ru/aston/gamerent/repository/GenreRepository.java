package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByTitleIn(List<GenreTitleEnum> titles);
}