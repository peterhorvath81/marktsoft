package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.owner.service.domain.Owner;
import com.marktsoft.practice.owner.service.OwnerService;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.service.domain.Pet;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.marktsoft.practice.owner.service.OwnerServiceTest.EMAIL;
import static com.marktsoft.practice.owner.service.OwnerServiceTest.PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PetServiceTest {

    public static final long ID = 1L;
    public static final String SPECIES = "cat";
    public static final String NAME = "Charlie";
    @Mock
    private PetRepository petRepository;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllPets() {
        Pet pet = createPet();
        when(petRepository.findAll()).thenReturn(List.of(pet));
        PetDTO petDTO = createPetDTO();

        List<PetDTO> result = petService.findAllPets();

        assertEquals(result, List.of(petDTO));
    }

    @Test
    public void shouldCreatePet() {
        Pet pet = createPet();
        Owner owner = createOwner();
        PetDTO petDTO = createPetDTO(pet);
        PetResponseDTO petResponseDTO = createPetResponseDTO();

        when(ownerService.findOwnerById(ID)).thenReturn(owner);
        pet.setPractice_owner(owner);
        when(petRepository.save(pet)).thenReturn(pet);

        PetResponseDTO result = petService.createPet(ID, petDTO);

        assertEquals(result, petResponseDTO);
    }

    @Test
    public void shouldUpdatePet() {
        Pet pet = createPet();
        PetDTO petDTO = createPetDTO(pet);
        PetResponseDTO petResponseDTO = createPetResponseDTOforUpdate();

        when(petRepository.findById(ID)).thenReturn(Optional.of(pet));
        when(petRepository.save(pet)).thenReturn(pet);

        PetResponseDTO result = petService.updatePet(ID, petDTO);

        assertEquals(result, petResponseDTO);
    }

    @Test
    public void shouldDeletePet() {
        doNothing().when(petRepository).deleteById(ID);

        petService.deletePet(ID);

        verify(petRepository,times(1)).deleteById(ID);
    }

    private PetDTO createPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setName(pet.getName());
        petDTO.setSpecies(pet.getSpecies());
        return petDTO;
    }

    private PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName(NAME);
        petDTO.setSpecies(SPECIES);
        return petDTO;
    }

    private Pet createPet() {
        return Pet
                .builder()
                .id(ID)
                .species(SPECIES)
                .name(NAME)
                .build();
    }

    private Owner createOwner() {
        return Owner
                .builder()
                .name(NAME)
                .id(ID)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    private PetResponseDTO createPetResponseDTO() {
        PetResponseDTO petResponseDTO = new PetResponseDTO();
        petResponseDTO.setSpecies(SPECIES);
        petResponseDTO.setName(NAME);
        return petResponseDTO;
    }

    private PetResponseDTO createPetResponseDTOforUpdate() {
        PetResponseDTO petResponseDTO = new PetResponseDTO();
        petResponseDTO.setId(ID);
        petResponseDTO.setSpecies(SPECIES);
        petResponseDTO.setName(NAME);
        return petResponseDTO;
    }
}