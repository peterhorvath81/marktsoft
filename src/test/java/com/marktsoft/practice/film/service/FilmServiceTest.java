package com.marktsoft.practice.film.service;

import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.domain.Film;
import com.marktsoft.practice.film.controller.dto.FilmDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FilmServiceTest {

//    public static final Integer ID = 1;
//    public static final String TITLE = "Asd";
//    private static final Integer RELESAE_YEAR = 1980;
//    @Mock
//    private FilmRepository filmRepository;
//
//    @InjectMocks
//    private FilmServiceImpl filmService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void shouldGetAllFilms() {
//        Film film = createFilm();
//        when(filmRepository.findAll()).thenReturn(List.of(film));
//        FilmDTO filmDTO = createFilmDTO();
//
//        List<FilmDTO> result = filmService.findAll();
//
//        assertEquals(result, List.of(filmDTO));
//    }
//
//    @Test
//    public void shouldCreateFilm() {
//        Film film = createFilm();
//        FilmDTO filmDTO = createFilmDTO(film);
//        FilmResponseDTO filmResponseDTO = createFilmResponseDTO();
//
//        when(filmRepository.save(film)).thenReturn(film);
//
//        FilmResponseDTO result = filmService.create(filmDTO);
//
//        assertEquals(result, filmResponseDTO);
//    }

//    @Test
//    public void shouldUpdatePet() {
//        Pet pet = createPet();
//        Owner owner = createOwner();
//        List<Owner> ownerList = new ArrayList<>();
//        ownerList.add(owner);
//        pet.setOwnerList(ownerList);
//        FilmUpdateDTO filmUpdateDTO = createPetUpdateDTO();
//        FilmResponseDTO filmResponseDTO = createPetResponseDTOforUpdate();
//
//        when(filmRepository.findById(ID)).thenReturn(Optional.of(pet));
//        when(filmRepository.save(pet)).thenReturn(pet);
//
//        FilmResponseDTO result = filmService.update(ID, filmUpdateDTO);
//
//        assertEquals(result, filmResponseDTO);
//    }
//
//    @Test
//    public void shouldDeleteFilm() {
//        Film film = createFilm();
//        when(filmRepository.findById(ID)).thenReturn(Optional.ofNullable(film));
//        doNothing().when(filmRepository).deleteById(ID);
//
//        filmService.delete(ID);
//
//        verify(filmRepository,times(1)).deleteById(ID);
//    }
//
//    private FilmDTO createFilmDTO(Film film) {
//        FilmDTO filmDTO = new FilmDTO();
//        filmDTO.setTitle(film.getTitle());
//        filmDTO.setReleaseYear(film.getReleaseYear());
//        filmDTO.setRentalDuration(film.getRentalDuration());
//        filmDTO.setRentalRate(film.getRentalRate());
//        filmDTO.setDescription(film.getDescription());
//        return filmDTO;
//    }
//
//    private FilmDTO createFilmDTO() {
//        FilmDTO filmDTO = new FilmDTO();
//        filmDTO.setTitle(TITLE);
//        filmDTO.setDescription("asd");
//        filmDTO.setRentalDuration((short) 1);
//        filmDTO.setRentalRate(new BigDecimal(1));
//        filmDTO.setReleaseYear(RELESAE_YEAR);
//        return filmDTO;
//    }
//
//    private Film createFilm() {
//        return Film
//                .builder()
////                .id(ID)
//                .description("asd")
//                .length((short) 90)
//                .releaseYear(RELESAE_YEAR)
//                .rentalDuration((short) 1)
//                .title(TITLE)
//                .rentalRate(new BigDecimal(2))
//                .replacementCost(new BigDecimal(1))
//                .lastUpdate(Instant.now())
//                .build();
//    }
//
//
//    private FilmResponseDTO createFilmResponseDTO() {
//        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
////        filmResponseDTO.setId(ID);
//        filmResponseDTO.setTitle(TITLE);
//        filmResponseDTO.setReleaseYear(RELESAE_YEAR);
//        filmResponseDTO.setDescription("asd");
//        return filmResponseDTO;
//    }
//

}
