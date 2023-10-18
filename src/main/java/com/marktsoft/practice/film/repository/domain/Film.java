package com.marktsoft.practice.film.repository.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String title;

    @Column(length = Integer.MAX_VALUE)
    private String description;

    private Integer releaseYear;

    @NotNull
    @Column(nullable = false)
    private Short rentalDuration;

    @NotNull
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private Short length;

    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @NotNull
    @Column(nullable = false)
    private Instant lastUpdate;

    private List<String> specialFeatures;

/*
    TODO [JPA Buddy] create field to map the 'rating' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "rating", columnDefinition = "mpaa_rating(0, 0)")
    private Object rating;
*/
/*
    TODO [JPA Buddy] create field to map the 'fulltext' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "fulltext", columnDefinition = "tsvector(0, 0) not null")
    private Object fulltext;
*/
}