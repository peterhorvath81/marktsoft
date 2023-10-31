package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDTO> getAll();

    List<CustomerDTO> getAllPaginated(Integer pageNumber, Integer pageCount);

    CustomerDTO findByIdWithSingleQuery(Integer id);

//    CustomerDTO findByIdWithDoubleQuery(Integer id);

//    CustomerResponseDTO create(CustomerDTO customerDTO);

    CustomerResponseDTO update(Integer id, CustomerDTO customerDTO);

    void delete(Integer id);
}
