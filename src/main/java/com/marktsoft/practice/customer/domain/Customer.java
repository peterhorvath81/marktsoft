package com.marktsoft.practice.customer.domain;

import com.marktsoft.practice.payment.domain.Payment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

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

    private List<Payment> payment;

    public Customer(Integer id, String firstName, String lastName, String email, List<Payment> payment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.payment = payment;
    }
}