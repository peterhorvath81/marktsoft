package com.marktsoft.practice.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private Integer paymentId;

    private Integer customerId;

    private Integer staffId;

    private Integer rentalId;

    private Long amount;

    private LocalDate payment_date;
}
