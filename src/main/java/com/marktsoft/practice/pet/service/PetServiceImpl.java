package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.exception.NotFoundRequestException;
import com.marktsoft.practice.owner.repository.domain.Owner;
import com.marktsoft.practice.owner.service.OwnerService;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.controller.dto.PetUpdateDTO;
import com.marktsoft.practice.pet.repository.domain.Pet;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.repository.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    private OwnerService ownerService;

    @Override
    public List<PetDTO> findAll() {
        List<Pet> petList = petRepository.findAll();
        log.info("Fetching all pets");
        return petList
                .stream()
                .map(pet -> PetDTO.builder()
                        .species(pet.getSpecies())
                        .name(pet.getName())
                        .build())
                .sorted(Comparator.comparing(PetDTO::getSpecies))
                .toList();

    }

    @Override
    @Transactional
    public PetResponseDTO create(Long ownerId, PetDTO petDTO) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = buildPet(petDTO, owner);
        pet.getOwnerList().add(owner);
        petRepository.save(pet);
        log.info("Updating owner");
        ownerService.updateWithPet(owner, pet);
        log.info("saving pet");
        return getPetResponseDTO(petDTO, pet);
    }

    @Override
    @Transactional
    public PetResponseDTO update(Long petId, PetUpdateDTO petUpdateDTO) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundRequestException("Pet with id: " + petId + " not found"));
        Owner owner = ownerService.findByName(petUpdateDTO.getOwnerDTO().getName());
        pet.setName(petUpdateDTO.getName());
        pet.setSpecies(petUpdateDTO.getSpecies());
        pet.getOwnerList().add(owner);
        ownerService.updateWithPet(owner, pet);
        log.info("updating pet");
        return getUpdatedPetResponseDTO(petUpdateDTO, pet);
    }

    @Override
    public void delete(Long id) {
        petRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Pet with id: " + id + " not found"));
        log.info("deleting pet");
        petRepository.deleteById(id);
    }

    private static PetResponseDTO getPetResponseDTO(PetDTO petDTO, Pet pet) {
        return PetResponseDTO.builder()
                .id(pet.getId())
                .species(petDTO.getSpecies())
                .name(pet.getName())
                .build();
    }

    private static PetResponseDTO getUpdatedPetResponseDTO(PetUpdateDTO petUpdateDTO, Pet pet) {
        return PetResponseDTO.builder()
                .id(pet.getId())
                .species(petUpdateDTO.getSpecies())
                .name(pet.getName())
                .build();
    }

    private static Pet buildPet(PetDTO petDTO, Owner owner) {
        return Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .ownerList(new ArrayList<>())
                .build();
    }
}
