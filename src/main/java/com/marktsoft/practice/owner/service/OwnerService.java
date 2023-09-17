package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.repository.domain.Owner;
import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.pet.repository.domain.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {

    List<OwnerDTO> getAll(Sort sort);

    List<OwnerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount);

    Owner findById(Long id);

    OwnerResponseDTO create(OwnerDTO ownerDTO);

    OwnerResponseDTO update(Long id, OwnerDTO ownerDTO);

    void delete(Long id);

    void updateWithPet(Owner owner, Pet pet);

    Owner findByName(String name);
}
