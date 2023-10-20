package com.marktsoft.practice.customer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResultRow {

    private String firstName;

    private String lastName;

    private String email;

    private Integer paymentId;

    private Long amount;

    private LocalDate paymentDate;
}
