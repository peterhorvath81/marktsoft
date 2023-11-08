package com.marktsoft.practice.customer.controller;

import com.marktsoft.practice.customer.CustomerResultExtractor.CustomerResultSetExtractor;
import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.PaginatedResponseDTO;
import com.marktsoft.practice.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static com.marktsoft.practice.customer.service.CustomerServiceImpl.*;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class CustomerControllerDBTest {

    public static final int PAGE_NUMBER = 1;
    public static final int PAGE_COUNT = 2;
    public static final int ID = 1;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerResultSetExtractor customerResultSetExtractor;

    @Test
    public void shouldGetAllCustomers() {
        List<CustomerDTO>  customerDTOList = customerController.getAll();
        List<Customer> customerList = jdbcTemplate.query(SQL_CUSTOMER_SELECT, customerResultSetExtractor);

        assertEquals(customerList.size(), 3);
        assertEquals(customerList.get(0).getFirstName(), "John");
        assertEquals(customerDTOList.size(), 3);
        assertEquals(customerDTOList.get(0).getFirstName(), "John");
    }

    @Test
    public void shouldGetAllPaginated() {
        PaginatedResponseDTO<CustomerDTO> responseDTO = customerController.getAllPaginated(PAGE_NUMBER, PAGE_COUNT);

        List<Customer> customerList = jdbcTemplate.query(SQL_CUSTOMER_PAGINATED, customerResultSetExtractor, PAGE_COUNT, 0);

        assertEquals(customerList.size(), 2);
        assertEquals(customerList.get(0).getPaymentList().size(), 2);
        assertEquals(customerList.get(0).getFirstName(), "John");
        assertEquals(responseDTO.getContent().size(), 2);
        assertEquals(responseDTO.getTotalPages(), 2);
        assertEquals(responseDTO.getTotalRecord(), 3);
    }

    @Test
    public void shouldGetCustomerWithSingleQuery() {
        CustomerDTO customerDTO = customerController.findByIdWithSingleQuery(ID);
        Customer customer = jdbcTemplate.query(SQL_FIND_BY_ID, customerResultSetExtractor, ID).get(0);

        assertEquals(customer.getFirstName(), "John");
        assertEquals(customer.getPaymentList().size(), 2);
        assertEquals(customerDTO.getFirstName(), "John");
        assertEquals(customerDTO.getPayments().size(), 2);

    }
}
