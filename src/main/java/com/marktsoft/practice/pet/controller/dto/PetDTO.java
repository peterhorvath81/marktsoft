package com.marktsoft.practice.pet.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonPropertyOrder({"firstName", "middleName", "lastName"}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {

    private String species;

    private String name;
}
