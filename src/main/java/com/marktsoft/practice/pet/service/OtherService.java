package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.exception.NotFoundRequestException;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.repository.PetRepository;
import com.marktsoft.practice.pet.repository.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class OtherService {

    private PetRepository petRepository;

    @Transactional
    public PetResponseDTO update(Long id, PetDTO petDTO) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Pet with id: " + id + " not found"));
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        log.info("updating pet");
        return getPetResponseDTO(petDTO, pet);
    }

    private static PetResponseDTO getPetResponseDTO(PetDTO petDTO, Pet pet) {
        return PetResponseDTO.builder()
                .id(pet.getId())
                .species(petDTO.getSpecies())
                .name(pet.getName())
                .build();
    }
}
