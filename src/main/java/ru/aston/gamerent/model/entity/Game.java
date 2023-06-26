package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "games")
public class Game {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false, columnDefinition = "CHARACTER VARYING(50)")
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id")
    private Developer developer;

    @Column(name = "release_date", nullable = false, columnDefinition = "DATE")
    private LocalDate releaseDate;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC(12,2)")
    private Double price;

    @Column(name = "image", nullable = false, columnDefinition = "CHARACTER VARYING(100)")
    private String image;

    @Column(name = "trailer_url", nullable = false, columnDefinition = "CHARACTER VARYING(250)")
    private String trailerUrl;

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "game")
    @ToString.Exclude
    private List<Screenshot> screenshots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return title == game.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}