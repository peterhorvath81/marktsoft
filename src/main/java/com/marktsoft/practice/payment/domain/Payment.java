package com.marktsoft.practice.payment.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Payment {

    private Integer paymentId;

    private Integer customerId;

    private Integer staffId;

    private Integer rentalId;

    private Long amount;

    private LocalDate payment_date;
}
