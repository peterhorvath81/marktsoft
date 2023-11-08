package com.marktsoft.practice.customer.domain;

import com.marktsoft.practice.customer.domain.mapper.CustomerMapper;
import com.marktsoft.practice.payment.domain.Payment;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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