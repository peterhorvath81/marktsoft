package com.marktsoft.practice.customer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDTO {

    private Integer totalRecord;

    private Integer totalPages;

    private Integer actualPage;
}
