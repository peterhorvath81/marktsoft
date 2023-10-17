package com.marktsoft.practice.film.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.film.controller.dto.FilmDTO;
import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.controller.dto.FilmUpdateDTO;
import com.marktsoft.practice.film.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    public static final Integer ID = 1;
    public static final String TITLE = "Asd";
    private static final Integer RELESAE_YEAR = 1980;

    @MockBean
    private FilmService filmService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllFilms() throws Exception {
        FilmDTO filmDTO = createFilmDTO();
        when(filmService.findAll()).thenReturn(List.of(filmDTO));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/film")
                .accept(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldRegisterFilm() throws Exception {
        FilmDTO filmDTO = createFilmDTO();
        FilmResponseDTO filmResponseDTO = createPetResponseDTO();
        when(filmService.create(filmDTO)).thenReturn(filmResponseDTO);

        mockMvc.perform(post("/film", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmDTO)))
                .andExpect(status().isOk());
    }

//    @Test
//    public void shouldUpdatePet() throws Exception {
//        FilmUpdateDTO filmUpdateDTO = createFilmUpdateDTO();
//        FilmResponseDTO filmResponseDTO = createPetResponseDTO();
//        when(filmService.update(ID, filmUpdateDTO)).thenReturn(filmResponseDTO);
//
//        mockMvc.perform(put("/pet/{id}", ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(filmUpdateDTO)))
//                .andExpect(status().isOk());
//    }

    @Test
    public void shouldDeletePet() throws Exception {
        doNothing().when(filmService).delete(ID);

        mockMvc.perform(delete("/film/{id}", ID))
                .andExpect(status().isOk());
    }

    private FilmDTO createFilmDTO() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(TITLE);
        filmDTO.setReleaseYear(RELESAE_YEAR);
        filmDTO.setRentalDuration((short) 10000);
        filmDTO.setRentalRate(new BigDecimal(2));
        filmDTO.setDescription("asd");
        return filmDTO;
    }

    private FilmResponseDTO createPetResponseDTO() {
        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
        filmResponseDTO.setId(ID);
        filmResponseDTO.setTitle(TITLE);
        filmResponseDTO.setReleaseYear(RELESAE_YEAR);
        filmResponseDTO.setDescription("asd");
        return filmResponseDTO;
    }

//    private FilmUpdateDTO createFilmUpdateDTO() {
//        FilmUpdateDTO filmUpdateDTO = new FilmUpdateDTO();
//        filmUpdateDTO.setName("testPet");
//        filmUpdateDTO.setSpecies(SPECIES);
//        filmUpdateDTO.setCustomerDTO(new CustomerDTO("Terry", "5873", "email@email.com"));
//        return filmUpdateDTO;
//    }
}
