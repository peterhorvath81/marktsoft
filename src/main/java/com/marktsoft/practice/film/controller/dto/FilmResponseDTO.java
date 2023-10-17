package com.marktsoft.practice.film.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmResponseDTO {

    private Integer id;

    private String title;

    private String description;

    private Integer releaseYear;
}
