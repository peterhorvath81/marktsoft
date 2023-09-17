package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.controller.dto.PetUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    List<PetDTO> findAll();

    PetResponseDTO create(Long id, PetDTO petDTO);

    PetResponseDTO update(Long id, PetUpdateDTO petUpdateDTO);

    void delete(Long id);
}
