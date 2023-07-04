package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.OrdersAccount;

@Repository
public interface OrderAccountRepository extends JpaRepository<OrdersAccount, Long> {
}
