package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.owner.domain.Owner;
import com.marktsoft.practice.owner.dto.OwnerDTO;
import com.marktsoft.practice.pet.domain.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OwnerService {

    List<OwnerDTO> getAllOwner();

    Owner findOwnerById(Long id);

    OwnerDTO createOwner(OwnerDTO ownerDTO);

    OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO);

    void deleteOwner(Long id);

    void updateOwnerWithPet(Owner owner, Pet pet);
}
