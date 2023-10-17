package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.repository.CustomerRepository;
import com.marktsoft.practice.customer.repository.domain.Customer;
import com.marktsoft.practice.exception.NotFoundRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll(Sort sort) {
        List<Customer> customerList = customerRepository.findAll(sort);
        log.info("Fetching customers");
        return getCustomerDTOList(customerList);
    }

    public List<CustomerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount) {
        Pageable pageable = PageRequest.of(pageNumber, pageCount, Sort.by(Sort.Direction.ASC, sortBy));
        List<Customer> customerList = customerRepository.findAll(pageable).get().toList();
        log.info("Fetching customers by pagination");
        return getCustomerDTOList(customerList);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Customer with id: " + id + " not found"));
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
