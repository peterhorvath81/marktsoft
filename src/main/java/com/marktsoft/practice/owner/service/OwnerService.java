package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.service.domain.Owner;
import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.pet.service.domain.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {

    List<OwnerDTO> getAllOwner();

    Owner findOwnerById(Long id);

    OwnerResponseDTO createOwner(OwnerDTO ownerDTO);

    OwnerResponseDTO updateOwner(Long id, OwnerDTO ownerDTO);

    void deleteOwner(Long id);

    void updateOwnerWithPet(Owner owner, Pet pet);
}
