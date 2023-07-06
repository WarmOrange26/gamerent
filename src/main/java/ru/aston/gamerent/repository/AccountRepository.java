package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    String ACCOUNTS_BY_USER_HQL = "FROM Account AS account " +
            "JOIN account.ordersAccounts AS orderAcc " +
            "JOIN orderAcc.order AS order " +
            "WHERE order.user = :user";

    String ACCOUNTS_BY_GAME_ID_HQL = "SELECT a FROM Account a JOIN a.accountsGame g WHERE g.game.id= :gameId";

    @Query(ACCOUNTS_BY_GAME_ID_HQL)
    List<Account> findByGameId(@Param("gameId") Long gameId);

    @Query(ACCOUNTS_BY_USER_HQL)
    List<Account> findAccountsByUser(User user);
}