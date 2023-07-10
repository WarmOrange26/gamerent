package ru.aston.gamerent.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tokens")
public class ConfirmationToken {

    @Id
    private Long id;

    private UUID token;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToOne
    @MapsId
    @ToString.Exclude
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        creationTime = LocalDateTime.now();
        token = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmationToken that)) return false;
        return Objects.equals(token, that.token) && Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, creationTime);
    }
}
