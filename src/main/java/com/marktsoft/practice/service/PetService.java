package com.marktsoft.practice.service;

import com.marktsoft.practice.dto.PetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    List<PetDTO> findAllPets();

    PetDTO createPet(Long id, PetDTO petDTO);

    PetDTO updatePet(Long id, PetDTO petDTO);

    void deletePet(Long id);
}
