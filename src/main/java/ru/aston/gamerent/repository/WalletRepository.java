package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    String WALLETS_BY_USER_HQL = "FROM Wallet w WHERE w.user = :user";

    @Query(WALLETS_BY_USER_HQL)
    List<Wallet> findWalletsByUser(User user);
}
