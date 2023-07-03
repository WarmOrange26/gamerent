package ru.aston.gamerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.gamerent.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
