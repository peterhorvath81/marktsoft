package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.repository.domain.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDTO> getAll(Sort sort);

    List<CustomerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount);

    Customer findById(Integer id);

    CustomerResponseDTO create(CustomerDTO customerDTO);

    CustomerResponseDTO update(Integer id, CustomerDTO customerDTO);

    void delete(Integer id);

//    void updateWithPet(Owner owner, Pet pet);
//
//    Owner findByName(String name);
}
