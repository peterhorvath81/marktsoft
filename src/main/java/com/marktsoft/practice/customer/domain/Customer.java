package com.marktsoft.practice.customer.domain;

import com.marktsoft.practice.payment.domain.Payment;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean activebool = false;

    private LocalDate createDate;

    private LocalDate lastUpdate;

    private Integer active;

    private List<Payment> paymentList = new ArrayList<>();
}