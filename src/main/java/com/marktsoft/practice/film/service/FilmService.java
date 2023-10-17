package com.marktsoft.practice.film.service;

import com.marktsoft.practice.film.controller.dto.FilmDTO;
import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.controller.dto.FilmUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    List<FilmDTO> findAll();

    FilmResponseDTO create(/*Long id,*/ FilmDTO filmDTO);

    FilmResponseDTO update(Integer filmId, FilmUpdateDTO filmUpdateDTO);

    void delete(Integer id);
}
