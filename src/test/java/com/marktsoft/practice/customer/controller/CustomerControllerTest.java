package com.marktsoft.practice.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.service.CustomerService;
import com.marktsoft.practice.payment.dto.PaymentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    public static final int PAGE_NUMBER = 1;
    public static final int PAGE_COUNT = 1;

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String EMAIL = "johndoe@gmail.com";
    public static final int PAYMENT_ID = 1;
    public static final long AMOUNT = 100L;
    public static final int ID = 1;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllCustomers() throws Exception {
        PaymentDTO paymentDTO = createPaymentDTO();
        CustomerDTO customerDTO = createCustomerDTO(paymentDTO);
        when(customerService.getAll()).thenReturn(List.of(customerDTO));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/customer")
                .accept(APPLICATION_JSON))
                .andReturn();

        List<CustomerDTO> actual = getActualResult(result);

        assertEquals(actual.size(),1);
        assertEquals(actual.get(0).getFirstName(), "John");
    }

    @Test
    public void shouldGetAllPaginated() throws Exception {
//        PaymentDTO paymentDTO = createPaymentDTO();
//        CustomerDTO customerDTO = createCustomerDTO(paymentDTO);
//        when(customerService.getAllPaginated(PAGE_NUMBER,PAGE_COUNT)).thenReturn(List.of(customerDTO));
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/customer/pages?pageNumber="+PAGE_NUMBER+"&pageCount="+PAGE_COUNT)
//                        .accept(APPLICATION_JSON))
//                .andReturn();
//
//        List<CustomerDTO> actual = getActualResult(result);
//
//        assertEquals(actual.size(),1);
//        assertEquals(actual.get(0).getFirstName(), "John");
    }

    @Test
    public void shouldGetCustomerWithSingleQuery() throws Exception {
        PaymentDTO paymentDTO = createPaymentDTO();
        CustomerDTO customerDTO = createCustomerDTO(paymentDTO);
        when(customerService.findByIdWithSingleQuery(ID)).thenReturn(customerDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/"+ID+"/singlequery")
                        .accept(APPLICATION_JSON))
                .andReturn();

        CustomerDTO actual = objectMapper.readValue(result.getResponse().getContentAsString(), CustomerDTO.class);

        assertEquals(actual.getFirstName(),"John");
        assertEquals(actual.getPayments().size(), 1);
    }

    private PaymentDTO createPaymentDTO() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(PAYMENT_ID);
        paymentDTO.setPayment_date(LocalDate.now());
        paymentDTO.setAmount(AMOUNT);
        return paymentDTO;
    }

    private CustomerDTO createCustomerDTO(PaymentDTO paymentDTO) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setEmail(EMAIL);
        customerDTO.setPayments(List.of(paymentDTO));
        return customerDTO;
    }

    private List<CustomerDTO> getActualResult(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CustomerDTO>>() {
        });
    }
}
