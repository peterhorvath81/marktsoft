package com.marktsoft.practice.owner.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerResponseDTO {

    private Long id;

    private String name;

    private String email;
}
