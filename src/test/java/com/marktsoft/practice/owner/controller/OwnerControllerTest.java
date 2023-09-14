package com.marktsoft.practice.owner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "John Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final String PHONE_NUMBER = "12345";

    @MockBean
    private OwnerService ownerService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllOwners() throws Exception {
//        OwnerDTO ownerDTO = createOwnerDTO();
//        when(ownerService.getAllOwner()).thenReturn(List.of(ownerDTO));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/owner")
//                .accept(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldRegisterOwner() throws Exception {
        OwnerDTO ownerDTO = createOwnerDTO();
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTO();
        when(ownerService.create(ownerDTO)).thenReturn(ownerResponseDTO);

        mockMvc.perform(post("/api/owner").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ownerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateOwner() throws Exception {
        OwnerDTO ownerDTO = createOwnerDTO();
        ownerDTO.setName("Jack Doe");
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTO();
        when(ownerService.update(ID, ownerDTO)).thenReturn(ownerResponseDTO);

        mockMvc.perform(put("/api/owner/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ownerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteOwner() throws Exception {
        doNothing().when(ownerService).delete(ID);

        mockMvc.perform(delete("/api/owner/{id}", ID))
                .andExpect(status().isOk());
    }

    private OwnerDTO createOwnerDTO() {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(NAME);
        ownerDTO.setEmail(EMAIL);
        ownerDTO.setPhoneNumber(PHONE_NUMBER);
        return ownerDTO;
    }

    private OwnerResponseDTO createOwnerResponseDTO() {
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO();
        ownerResponseDTO.setId(ID);
        ownerResponseDTO.setName(NAME);
        return ownerResponseDTO;
    }
}
