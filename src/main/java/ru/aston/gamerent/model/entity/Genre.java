package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Column(unique = true, nullable = false)
    private GenreTitleEnum title;

    @ManyToMany(mappedBy = "genres")
    @ToString.Exclude
    private Set<Game> games;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre that)) return false;
        return title == that.getTitle();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}