package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("FROM Wallet w WHERE w.user = :user")
    List<Wallet> findWalletsByUser(User user);

    @Query("FROM Account AS account " +
            "JOIN account.ordersAccounts AS orderAcc " +
            "JOIN orderAcc.order AS order " +
            "WHERE order.user = :user")
    List<Account> findAccountsByUser(User user);
}
