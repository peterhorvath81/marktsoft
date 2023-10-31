package com.marktsoft.practice.customer.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedCustomerResponseDTO {

    private List<CustomerDTO> customerDTOList;

    private PageDTO pageDTO;

}
