package com.marktsoft.practice.film.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {

    private String title;

    private String description;

    private Integer releaseYear;

    private Short rentalDuration;

    private BigDecimal rentalRate;
}
