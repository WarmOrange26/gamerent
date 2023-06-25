package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenreTitleEnum title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return title == genre.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}