package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.PostponedGame;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<PostponedGame, Long> {
    String POSTPONED_GAME_BY_USER_NAME_AND_GAME_ID_HQL = """
            SELECT pg FROM PostponedGame pg 
            WHERE pg.user.email = :email AND pg.game.id = :gameId""";
    String POSTPONED_USER_ID_GAME_ID_HQL = "SELECT pg FROM PostponedGame pg WHERE pg.user.email = :email";

    @Query(POSTPONED_GAME_BY_USER_NAME_AND_GAME_ID_HQL)
    Optional<PostponedGame> findByUserEmailAndGameId(@Param("email") String email, @Param("gameId") Long gameId);

    @Query(POSTPONED_USER_ID_GAME_ID_HQL)
    List<PostponedGame> findByUserEmail(@Param("email") String email);
}
