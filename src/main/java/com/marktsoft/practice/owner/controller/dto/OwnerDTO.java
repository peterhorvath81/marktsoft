package com.marktsoft.practice.owner.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDTO {

    private String name;

    private String phoneNumber;

    @NotBlank(message = "the e-mail address can not be null")
    private String email;
}
