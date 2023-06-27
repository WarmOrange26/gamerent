package ru.aston.gamerent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders_account")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Account account;

    @Column(nullable = false)
    private int periods;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdersAccount)) return false;
        OrdersAccount that = (OrdersAccount) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}