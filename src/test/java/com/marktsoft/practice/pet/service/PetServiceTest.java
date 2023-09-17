package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.repository.domain.Owner;
import com.marktsoft.practice.owner.service.OwnerService;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.controller.dto.PetUpdateDTO;
import com.marktsoft.practice.pet.repository.domain.Pet;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        List<PetDTO> result = petService.findAll();

        assertEquals(result, List.of(petDTO));
    }

    @Test
    public void shouldCreatePet() {
        Pet pet = createPet();
        Owner owner = createOwner();
        PetDTO petDTO = createPetDTO(pet);
        PetResponseDTO petResponseDTO = createPetResponseDTO();

        when(ownerService.findById(ID)).thenReturn(owner);
        pet.setOwnerList(List.of(owner));
        when(petRepository.save(pet)).thenReturn(pet);

        PetResponseDTO result = petService.create(ID, petDTO);

        assertEquals(result, petResponseDTO);
    }

    @Test
    public void shouldUpdatePet() {
        Pet pet = createPet();
        Owner owner = createOwner();
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner);
        pet.setOwnerList(ownerList);
        PetUpdateDTO petUpdateDTO = createPetUpdateDTO();
        PetResponseDTO petResponseDTO = createPetResponseDTOforUpdate();

        when(petRepository.findById(ID)).thenReturn(Optional.of(pet));
        when(petRepository.save(pet)).thenReturn(pet);

        PetResponseDTO result = petService.update(ID, petUpdateDTO);

        assertEquals(result, petResponseDTO);
    }

    @Test
    public void shouldDeletePet() {
        Pet pet = createPet();
        when(petRepository.findById(ID)).thenReturn(Optional.ofNullable(pet));
        doNothing().when(petRepository).deleteById(ID);

        petService.delete(ID);

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

    private PetUpdateDTO createPetUpdateDTO() {
        PetUpdateDTO petUpdateDTO = new PetUpdateDTO();
        petUpdateDTO.setName(NAME);
        petUpdateDTO.setSpecies(SPECIES);
        petUpdateDTO.setOwnerDTO(new OwnerDTO("Terry", "5873", "email@email.com"));
        return  petUpdateDTO;
    }
}
