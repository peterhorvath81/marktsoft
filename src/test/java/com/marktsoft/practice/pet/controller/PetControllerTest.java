package com.marktsoft.practice.pet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Charlie";
    public static final String SPECIES = "Cat";

    @MockBean
    private PetService petService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllPets() throws Exception {
        PetDTO petDTO = createPetDTO();
        when(petService.findAll()).thenReturn(List.of(petDTO));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pet")
                .accept(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldRegisterPet() throws Exception {
        PetDTO petDTO = createPetDTO();
        PetResponseDTO petResponseDTO = createPetResponseDTO();
        when(petService.create(ID,petDTO)).thenReturn(petResponseDTO);

        mockMvc.perform(post("/api/pet/owner/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdatePet() throws Exception {
        PetDTO petDTO = createPetDTO();
        petDTO.setName("Terry");
        PetResponseDTO petResponseDTO = createPetResponseDTO();
        when(petService.update(ID,petDTO)).thenReturn(petResponseDTO);

        mockMvc.perform(put("/api/pet/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeletePet() throws Exception {
        doNothing().when(petService).delete(ID);

        mockMvc.perform(delete("/api/pet/{id}", ID))
                .andExpect(status().isOk());
    }

    private PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName(NAME);
        petDTO.setSpecies(SPECIES);
        return petDTO;
    }

    private PetResponseDTO createPetResponseDTO() {
        PetResponseDTO petResponseDTO = new PetResponseDTO();
        petResponseDTO.setId(ID);
        petResponseDTO.setSpecies(SPECIES);
        petResponseDTO.setName(NAME);
        return petResponseDTO;
    }
}
