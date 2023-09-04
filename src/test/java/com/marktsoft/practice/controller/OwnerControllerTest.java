package com.marktsoft.practice.controller;

import com.marktsoft.practice.dto.OwnerDTO;
import com.marktsoft.practice.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "John Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final String PHONE_NUMBER = "12345";
    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllOwners() {
        OwnerDTO ownerDTO = createOwnerDTO();
        when(ownerService.getAllOwner()).thenReturn(List.of(ownerDTO));

        List<OwnerDTO> result = ownerController.getAllOwners().getBody();

        assertEquals(result, List.of(ownerDTO));
    }

    @Test
    public void shouldRegisterOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        when(ownerService.createOwner(ownerDTO)).thenReturn(ownerDTO);

        OwnerDTO result = ownerController.registerOwner(ownerDTO).getBody();

        assertEquals(result, ownerDTO);
    }

    @Test
    public void shouldUpdateOwner() {
        OwnerDTO ownerDTO = createOwnerDTO();
        ownerDTO.setName("Jack Doe");
        when(ownerService.updateOwner(ID, ownerDTO)).thenReturn(ownerDTO);

        OwnerDTO result = ownerController.updateOwner(ID, ownerDTO).getBody();

        assertEquals(result, ownerDTO);
    }

    @Test
    public void shouldDeleteOwner() {
        doNothing().when(ownerService).deleteOwner(ID);

        ownerController.deleteOwner(ID);

        verify(ownerService, times(1)).deleteOwner(ID);
    }

    private OwnerDTO createOwnerDTO() {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(NAME);
        ownerDTO.setEmail(EMAIL);
        ownerDTO.setPhoneNumber(PHONE_NUMBER);
        return ownerDTO;
    }
}
