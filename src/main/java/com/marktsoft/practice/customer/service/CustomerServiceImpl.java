package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.repository.CustomerRepository;
import com.marktsoft.practice.customer.repository.domain.Customer;
import com.marktsoft.practice.exception.NotFoundRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/dvdrental";
    public static final String USER = "postgres";
    public static final String PASSWORD = "password";

    private CustomerRepository customerRepository;

    @Getter
    private ApplicationContext applicationContext;


    @Override
    public List<CustomerDTO> getAll() {

        DataSource ds = (DataSource) getApplicationContext().getBean("dataSource");

        List<CustomerDTO> customerList = new ArrayList<>();

        String SQL_SELECT = "Select * from customer";

        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {

            try(ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {

                while (resultSet.next()) {

                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");

                    CustomerDTO customerDTO = new CustomerDTO(firstName, lastName, email);

                    customerList.add(customerDTO);
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException("Error occurred while executing the following SQL query: "
                    + SQL_SELECT+ e.getMessage());
        }

        log.info("Fetching customers");
        return customerList;
    }

    public List<CustomerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount) {
        Pageable pageable = PageRequest.of(pageNumber, pageCount, Sort.by(Sort.Direction.ASC, sortBy));
        List<Customer> customerList = customerRepository.findAll(pageable).get().toList();
        log.info("Fetching customers by pagination");
        return getCustomerDTOList(customerList);
    }

    @Override
    public CustomerDTO findById(Integer id) {

        CustomerDTO customerDTO = new CustomerDTO();

        String SQL_SELECT = "Select * from customer where customer_id=?";
        
        try (Connection connection = DriverManager
                .getConnection(JDBC_CONNECTION, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT)) {

            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");

                    if (firstName!=null && lastName!=null && email!=null) {
                        customerDTO.setFirstName(firstName);
                        customerDTO.setLastName(lastName);
                        customerDTO.setEmail(email);
                    }
                    else {
                        throw new NotFoundRequestException("The customer with id: "+id+"not found");
                    }
                }
            }


        } catch(SQLException e) {
            throw new RuntimeException("Error occurred while executing the following SQL query: "
                    + SQL_SELECT+ e.getMessage());
        }
        return customerDTO;
    }

    @Override
    public CustomerResponseDTO create(CustomerDTO customerDTO) {
        Customer customer = getCustomer(customerDTO);
        log.info("Saving customer");
        customerRepository.save(customer);
        return getCustomerResponseDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Integer id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        log.info("Updating customer");
        return getCustomerResponseDTO(customer);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
        log.info("deleting customer");
        customerRepository.delete(customer);
    }

//    @Override
//    public void updateWithPet(Owner owner, Pet pet) {
//        owner.getPetList().add(pet);
//        customerRepository.save(owner);
//    }
//
//    @Override
//    public Owner findByName(String name) {
//        return customerRepository.findByName(name);
//    }

    private static List<CustomerDTO> getCustomerDTOList(List<Customer> customerList) {
        return customerList.stream().map(customer -> CustomerDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build()).toList();
    }

    private static CustomerResponseDTO getCustomerResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }

    private static Customer getCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .activebool(true)
                .createDate(LocalDate.now())
                .lastUpdate(Instant.now())
                .build();
    }
}
