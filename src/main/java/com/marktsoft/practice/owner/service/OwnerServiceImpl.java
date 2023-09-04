package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.owner.domain.Owner;
import com.marktsoft.practice.owner.dto.OwnerDTO;
import com.marktsoft.practice.owner.repository.OwnerRepository;
import com.marktsoft.practice.pet.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    @Override
    public List<OwnerDTO> getAllOwner() {
        List<Owner> ownerList = ownerRepository.findAll();
        log.info("Fetching owners");
        return ownerList
                .stream()
                .map(owner -> OwnerDTO.builder()
                        .name(owner.getName())
                        .phoneNumber(owner.getPhoneNumber())
                        .email(owner.getEmail()).build())
                .toList();
    }

    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner with id: " + id + "not found"));
    }

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = Owner.builder()
                .name(ownerDTO.getName())
                .email(ownerDTO.getEmail())
                .phoneNumber(ownerDTO.getPhoneNumber())
                .build();
        log.info("Saving owner");
        ownerRepository.save(owner);

        return ownerDTO;
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The owner with the id " +id+ "not found"));
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        log.info("Updating owner");
        ownerRepository.save(owner);
        return ownerDTO;
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The owner with the id " +id+ "not found"));
        owner.setPet(null);
        log.info("deleting owner");
        ownerRepository.delete(owner);
    }

    @Override
    public void updateOwnerWithPet(Owner owner, Pet pet) {
        List<Pet> petList = new ArrayList<>();
        petList.add(pet);
        owner.setPet(petList);
        ownerRepository.save(owner);
    }
}
