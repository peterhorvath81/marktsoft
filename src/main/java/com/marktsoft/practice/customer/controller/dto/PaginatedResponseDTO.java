package com.marktsoft.practice.customer.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponseDTO<T> {

    private List<T> content;

    private Integer totalRecord;

    private Integer totalPages;

    private Integer actualPage;
}
