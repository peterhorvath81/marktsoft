package com.marktsoft.practice.customer.repository.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean activebool = false;

    private LocalDate createDate;

    private LocalDate lastUpdate;

    private Integer active;

}