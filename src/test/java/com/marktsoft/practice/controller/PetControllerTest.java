package com.marktsoft.practice.controller;

import com.marktsoft.practice.dto.PetDTO;
import com.marktsoft.practice.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Charlie";
    public static final String SPECIES = "Cat";
    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllPets() {
        PetDTO petDTO = createPetDTO();
        when(petService.findAllPets()).thenReturn(List.of(petDTO));

        List<PetDTO> result = petController.getAllPets().getBody();

        assertEquals(result, List.of(petDTO));
    }

    @Test
    public void shouldRegisterPet() {
        PetDTO petDTO = createPetDTO();
        when(petService.createPet(ID,petDTO)).thenReturn(petDTO);

        PetDTO result = petController.registerPet(ID, petDTO).getBody();

        assertEquals(result, petDTO);
    }

    @Test
    public void shouldUpdatePet() {
        PetDTO petDTO = createPetDTO();
        petDTO.setName("Terry");
        when(petService.updatePet(ID,petDTO)).thenReturn(petDTO);

        PetDTO result = petController.updatePet(ID, petDTO).getBody();

        assertEquals(result, petDTO);
    }

    @Test
    public void shouldDeletePet() {
        doNothing().when(petService).deletePet(ID);

        petController.deletePet(ID);

        verify(petService, times(1)).deletePet(ID);
    }

    private PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName(NAME);
        petDTO.setSpecies(SPECIES);
        return petDTO;
    }
}
