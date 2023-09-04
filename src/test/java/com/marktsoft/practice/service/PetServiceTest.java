package com.marktsoft.practice.service;

import com.marktsoft.practice.domain.Owner;
import com.marktsoft.practice.domain.Pet;
import com.marktsoft.practice.dto.PetDTO;
import com.marktsoft.practice.repository.OwnerRepository;
import com.marktsoft.practice.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.marktsoft.practice.service.OwnerServiceTest.EMAIL;
import static com.marktsoft.practice.service.OwnerServiceTest.PHONE_NUMBER;
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
    private OwnerRepository ownerRepository;

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

        when(ownerRepository.findById(ID)).thenReturn(Optional.of(owner));
        pet.setOwner(owner);
        when(petRepository.save(pet)).thenReturn(pet);

        PetDTO result = petService.createPet(ID, petDTO);

        assertEquals(result, petDTO);
    }

    @Test
    public void shouldUpdatePet() {
        Pet pet = createPet();
        PetDTO petDTO = createPetDTO(pet);

        when(petRepository.findById(ID)).thenReturn(Optional.of(pet));
        when(petRepository.save(pet)).thenReturn(pet);

        PetDTO result = petService.updatePet(ID, petDTO);

        assertEquals(result, petDTO);
    }

    @Test
    public void shouldDeletePet() {
        Owner owner = createOwner();
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
        Pet pet = new Pet();
        pet.setId(ID);
        pet.setSpecies(SPECIES);
        pet.setName(NAME);
        return pet;
    }

    private Owner createOwner() {
        Owner owner = new Owner();
        owner.setName(NAME);
        owner.setId(ID);
        owner.setEmail(EMAIL);
        owner.setPhoneNumber(PHONE_NUMBER);
        return owner;
    }
}
