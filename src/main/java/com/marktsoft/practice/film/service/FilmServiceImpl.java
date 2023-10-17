package com.marktsoft.practice.film.service;

import com.marktsoft.practice.exception.NotFoundRequestException;
import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.controller.dto.FilmUpdateDTO;
import com.marktsoft.practice.film.repository.domain.Film;
import com.marktsoft.practice.film.controller.dto.FilmDTO;
import com.marktsoft.practice.film.repository.FilmRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

//    private CustomerService customerService;

    @Override
    public List<FilmDTO> findAll() {
        List<Film> filmList = filmRepository.findAll();
        log.info("Fetching all films");
        return filmList
                .stream()
                .map(film -> FilmDTO.builder()
                        .title(film.getTitle())
                        .description(film.getDescription())
                        .releaseYear(film.getReleaseYear())
                        .rentalDuration(film.getRentalDuration())
                        .rentalRate(film.getRentalRate())
                        .build())
                .sorted(Comparator.comparing(FilmDTO::getReleaseYear))
                .toList();

    }

    @Override
    public FilmResponseDTO create(FilmDTO filmDTO) {
        Film film = buildFilm(filmDTO);
        filmRepository.save(film);
        log.info("Saving film");
        return getFilmResponseDTO(filmDTO, film);
    }

    @Override
    @Transactional
    public FilmResponseDTO update(Integer filmId, FilmUpdateDTO filmUpdateDTO) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundRequestException("Film with id: " + filmId + " not found"));
        film.setTitle(filmUpdateDTO.getTitle());
        film.setDescription(filmUpdateDTO.getDescription());
        film.setReleaseYear(filmUpdateDTO.getReleaseYear());
        log.info("updating film");
        return getUpdatedFilmResponseDTO(filmId, filmUpdateDTO);
    }

    @Override
    public void delete(Integer id) {
        filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("film with id: " + id + " not found"));
        log.info("deleting film");
        filmRepository.deleteById(id);
    }

    private static FilmResponseDTO getFilmResponseDTO(FilmDTO filmDTO, Film film) {
        return FilmResponseDTO.builder()
                .id(film.getId())
                .title(filmDTO.getTitle())
                .description(filmDTO.getDescription())
                .releaseYear(filmDTO.getReleaseYear())
                .build();
    }

    private static FilmResponseDTO getUpdatedFilmResponseDTO(Integer filmId, FilmUpdateDTO filmUpdateDTO) {
        return FilmResponseDTO.builder()
                .id(filmId)
                .title(filmUpdateDTO.getTitle())
                .description(filmUpdateDTO.getDescription())
                .releaseYear(filmUpdateDTO.getReleaseYear())
                .build();
    }

    private static Film buildFilm(FilmDTO filmDTO) {
        return Film.builder()
                .title(filmDTO.getTitle())
                .description(filmDTO.getDescription())
                .releaseYear(filmDTO.getReleaseYear())
                .rentalDuration(filmDTO.getRentalDuration())
                .rentalRate(filmDTO.getRentalRate())
                .build();
    }
}
