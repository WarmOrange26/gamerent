package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Developer developer;

    @Column(name = "release_date")
    private Date releaseDate;

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