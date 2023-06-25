package ru.aston.gamerent.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_roles")
public class UsersRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column
    private LocalDateTime assigningTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersRole usersRole)) return false;
        return Objects.equals(id, usersRole.id) && Objects.equals(user, usersRole.user) && Objects.equals(role, usersRole.role) && Objects.equals(assigningTime, usersRole.assigningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, role, assigningTime);
    }

    @Override
    public String toString() {
        return "UsersRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                ", assigningTime=" + assigningTime +
                '}';
    }
}