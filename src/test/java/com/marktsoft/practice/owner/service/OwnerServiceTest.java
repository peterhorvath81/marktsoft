package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.repository.domain.Owner;
import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.repository.OwnerRepository;
import com.marktsoft.practice.pet.repository.domain.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.marktsoft.practice.pet.service.PetServiceTest.SPECIES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "John Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final String PHONE_NUMBER = "1234";
    public static final String SORT_BY = "NAME";
    public static final String SORT_DIRECTION = "DESC";
    public static final Integer PAGE_NUMBER = 0;
    public static final Integer PAGE_COUNT = 1;


    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllOwners() {
        Owner owner = createOwner();

        Sort sort = Sort.by(SORT_DIRECTION, SORT_BY);

        when(ownerRepository.findAll(sort)).thenReturn(List.of(owner));

        OwnerDTO ownerDTO = createOwnerDTO(owner);

        List<OwnerDTO> result = ownerService.getAll(sort);

        assertEquals(result, List.of(ownerDTO));
    }

    @Test
    public void shouldGetAllOwnersPaginated() {
        Owner owner = createOwner();

        Page<Owner> ownerPage = mock(Page.class);

        when(ownerRepository.findAll(isA(Pageable.class))).thenReturn(ownerPage);

        OwnerDTO ownerDTO = createOwnerDTO(owner);

        List<OwnerDTO> result = ownerService.getAllPaginated(SORT_BY, PAGE_NUMBER, PAGE_COUNT);

        assertEquals(result, Collections.emptyList());
    }

    @Test
    public void shouldFindById() {
        Owner owner = createOwner();
        when(ownerRepository.findById(ID)).thenReturn(Optional.ofNullable(owner));

        Owner result = ownerService.findById(ID);

        assertEquals(result, owner);
    }

    @Test
    public void shouldCreateOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        Owner owner = createOwner(ownerDTO);
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTO();
        when(ownerRepository.save(owner)).thenReturn(owner);

        OwnerResponseDTO result = ownerService.create(ownerDTO);

        assertEquals(result, ownerResponseDTO);
    }

    @Test
    public void shouldUpdateOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        Owner owner = createOwner(ownerDTO);
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTOforUpdate();
        when(ownerRepository.findById(ID)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(owner)).thenReturn(owner);

        OwnerResponseDTO result = ownerService.update(ID, ownerDTO);

        assertEquals(result, ownerResponseDTO);
    }

    @Test
    public void shouldUpdateOwnerWithPet() {
        Owner owner = createOwner();
        Pet pet = createPet();
        List<Pet> petList = new ArrayList<>();
        petList.add(pet);
        owner.setPetList(petList);

        when(ownerRepository.save(owner)).thenReturn(owner);

        ownerService.updateWithPet(owner,pet);

        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    public void shouldFindOwnerByName() {
        Owner owner = createOwner();
        when(ownerRepository.findByName(NAME)).thenReturn(owner);

        Owner result = ownerService.findByName(NAME);

        assertEquals(result, owner);
    }

    @Test
    public void shouldDeleteOwner() {
        Owner owner = createOwner();
        when(ownerRepository.findById(ID)).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).delete(owner);

        ownerService.delete(ID);

        verify(ownerRepository,times(1)).findById(ID);
        verify(ownerRepository, times(1)).delete(owner);
    }

    private Owner createOwner(OwnerDTO ownerDTO) {
        return Owner.builder()
                .name(ownerDTO.getName())
                .id(ID)
                .email(ownerDTO.getEmail())
                .phoneNumber(ownerDTO.getPhoneNumber())
                .build();
    }

    private OwnerDTO createOwnerDTO() {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(NAME);
        ownerDTO.setEmail(EMAIL);
        ownerDTO.setPhoneNumber(PHONE_NUMBER);
        return ownerDTO;
    }

    private OwnerDTO createOwnerDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(owner.getName());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setPhoneNumber(owner.getPhoneNumber());
        return ownerDTO;
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
    private OwnerResponseDTO createOwnerResponseDTO() {
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO();
        ownerResponseDTO.setName(NAME);
        ownerResponseDTO.setEmail(EMAIL);
        return ownerResponseDTO;
    }

    private OwnerResponseDTO createOwnerResponseDTOforUpdate() {
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO();
        ownerResponseDTO.setId(ID);
        ownerResponseDTO.setName(NAME);
        ownerResponseDTO.setEmail(EMAIL);
        return ownerResponseDTO;
    }

    private Pet createPet() {
        return Pet
                .builder()
                .id(ID)
                .species(SPECIES)
                .name(NAME)
                .build();
    }
}
