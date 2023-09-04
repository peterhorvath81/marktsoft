package com.marktsoft.practice.service;

import com.marktsoft.practice.domain.Owner;
import com.marktsoft.practice.dto.OwnerDTO;
import com.marktsoft.practice.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "John Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final String PHONE_NUMBER = "1234";
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
        when(ownerRepository.findAll()).thenReturn(List.of(owner));

        OwnerDTO ownerDTO = createOwnerDTO(owner);

        List<OwnerDTO> result = ownerService.getAllOwner();

        assertEquals(result, List.of(ownerDTO));
    }

    @Test
    public void shouldCreateOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        Owner owner = createOwner(ownerDTO);
        when(ownerRepository.save(owner)).thenReturn(owner);

        OwnerDTO result = ownerService.createOwner(ownerDTO);

        assertEquals(result, ownerDTO);
    }

    @Test
    public void shouldUpdateOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        Owner owner = createOwner(ownerDTO);
        when(ownerRepository.findById(ID)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(owner)).thenReturn(owner);

        OwnerDTO result = ownerService.updateOwner(ID, ownerDTO);

        assertEquals(result, ownerDTO);
    }

    @Test
    public void shouldDeleteOwner() {
        Owner owner = createOwner();
        when(ownerRepository.findById(ID)).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).delete(owner);

        ownerService.deleteOwner(ID);

        verify(ownerRepository,times(1)).findById(ID);
        verify(ownerRepository, times(1)).delete(owner);
    }

    private Owner createOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setId(ID);
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        return owner;
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
        Owner owner = new Owner();
        owner.setName(NAME);
        owner.setId(ID);
        owner.setEmail(EMAIL);
        owner.setPhoneNumber(PHONE_NUMBER);
        return owner;
    }
}
