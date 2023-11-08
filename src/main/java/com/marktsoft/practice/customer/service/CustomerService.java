package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.controller.dto.PaginatedResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDTO> getAll();

    PaginatedResponseDTO<CustomerDTO> getAllPaginated(int pageNumber, int pageCount);

    CustomerDTO findByIdWithSingleQuery(Integer id);

//    CustomerDTO findByIdWithDoubleQuery(Integer id);

//    CustomerResponseDTO create(CustomerDTO customerDTO);

    CustomerResponseDTO update(Integer id, CustomerDTO customerDTO);

    void delete(Integer id);
}
