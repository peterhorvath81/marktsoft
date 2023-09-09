package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    List<PetDTO> findAllPets();

    PetResponseDTO createPet(Long id, PetDTO petDTO);

    PetResponseDTO updatePet(Long id, PetDTO petDTO);

    void deletePet(Long id);
}
