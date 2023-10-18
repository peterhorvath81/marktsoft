package com.marktsoft.practice.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    public static final Integer ID = 1;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final String PHONE_NUMBER = "12345";
    public static final String SORT_DIRECTION = "ASC";
    public static final int PAGE_NUMBER = 1;
    public static final int PAGE_COUNT = 1;

    @MockBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllCustomers() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        when(customerService.getAll()).thenReturn(List.of(customerDTO));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer")
                .accept(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllCustomersPaginated() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();

        when(customerService.getAllPaginated(SORT_DIRECTION, PAGE_NUMBER, PAGE_COUNT)).thenReturn(List.of(customerDTO));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer/pages?sortBy=firstName&pageNumber=1&pageCount=1")
                .accept(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldRegisterCustomer() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO();
        when(customerService.create(customerDTO)).thenReturn(customerResponseDTO);

        mockMvc.perform(post("/customer").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateOwner() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO();
        when(customerService.update(ID, customerDTO)).thenReturn(customerResponseDTO);

        mockMvc.perform(put("/customer/{id}", ID)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        doNothing().when(customerService).delete(ID);

        mockMvc.perform(delete("/customer/{id}", ID))
                .andExpect(status().isOk());
    }

    private CustomerDTO createCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setEmail(EMAIL);
        return customerDTO;
    }

    private CustomerResponseDTO createCustomerResponseDTO() {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(ID);
        customerResponseDTO.setFirstName(FIRST_NAME);
        customerResponseDTO.setLastName(LAST_NAME);
        customerResponseDTO.setEmail(EMAIL);
        return customerResponseDTO;
    }
}
