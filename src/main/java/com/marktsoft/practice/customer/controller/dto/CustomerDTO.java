package com.marktsoft.practice.customer.controller.dto;

import com.marktsoft.practice.payment.dto.PaymentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private String firstName;

    private String lastName;

    @NotNull(message = "E-mail can not be null")
    private String email;

    private List<PaymentDTO> payments;
}
