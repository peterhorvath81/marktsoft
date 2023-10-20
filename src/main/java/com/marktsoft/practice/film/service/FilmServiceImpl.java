package com.marktsoft.practice.film.service;

import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.controller.dto.FilmUpdateDTO;
import com.marktsoft.practice.film.domain.Film;
import com.marktsoft.practice.film.controller.dto.FilmDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/dvdrental";
    public static final String USER = "postgres";
    public static final String PASSWORD = "password";


    @Override
    public List<FilmDTO> findAll() {

        List<FilmDTO> filmDTOList = new ArrayList<>();

        String SQL_QUERY = "select * from film";

        try (Connection connection = DriverManager
                .getConnection(JDBC_CONNECTION, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            try(ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {

                while (resultSet.next()) {

                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    Integer releaseYear = resultSet.getInt("release_year");
                    Short rentalDuration = resultSet.getShort("rental_duration");
                    BigDecimal rentalRate = resultSet.getBigDecimal("rental_rate");

                    FilmDTO filmDTO = new FilmDTO(title, description, releaseYear, rentalDuration, rentalRate);

                    filmDTOList.add(filmDTO);
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException("Error occurred while executing the following SQL query: "
                    + SQL_QUERY+ e.getMessage());
        }
        return filmDTOList;
    }

    @Override
    public FilmDTO findById(Integer id) {

        FilmDTO filmDTO = new FilmDTO();

        String SQL_QUERY = "select * from film where film_id=?";

        try (Connection connection = DriverManager
                .getConnection(JDBC_CONNECTION, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY)) {

            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    Integer releaseYear = resultSet.getInt("release_year");
                    Short rentalDuration = resultSet.getShort("rental_duration");
                    BigDecimal rentalRate = resultSet.getBigDecimal("rental_rate");

                    filmDTO.setTitle(title);
                    filmDTO.setDescription(description);
                    filmDTO.setReleaseYear(releaseYear);
                    filmDTO.setRentalDuration(rentalDuration);
                    filmDTO.setRentalRate(rentalRate);
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException("Error occurred while executing the following SQL query: "
                    + SQL_QUERY+ e.getMessage());
        }
        return filmDTO;
    }

    @Override
    public FilmResponseDTO create(FilmDTO filmDTO) {
        Film film = buildFilm(filmDTO);
//        filmRepository.save(film);
        log.info("Saving film");
        return getFilmResponseDTO(filmDTO, film);
    }

    @Override
    public FilmResponseDTO update(Integer filmId, FilmUpdateDTO filmUpdateDTO) {
//        Film film = filmRepository.findById(filmId)
//                .orElseThrow(() -> new NotFoundRequestException("Film with id: " + filmId + " not found"));
//        film.setTitle(filmUpdateDTO.getTitle());
//        film.setDescription(filmUpdateDTO.getDescription());
//        film.setReleaseYear(filmUpdateDTO.getReleaseYear());
        log.info("updating film");
        return getUpdatedFilmResponseDTO(filmId, filmUpdateDTO);
    }

    @Override
    public void delete(Integer id) {
//        filmRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("film with id: " + id + " not found"));
//        log.info("deleting film");
//        filmRepository.deleteById(id);
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
