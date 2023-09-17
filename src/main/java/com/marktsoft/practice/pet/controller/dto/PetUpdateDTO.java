package com.marktsoft.practice.pet.controller.dto;

import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetUpdateDTO {

    private String species;

    private String name;

    private OwnerDTO ownerDTO;
}
