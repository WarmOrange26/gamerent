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

    @Column
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id")
    private Developer developer;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private String image;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "game")
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