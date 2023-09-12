package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.exception.ApiRequestException;
import com.marktsoft.practice.owner.service.domain.Owner;
import com.marktsoft.practice.owner.service.OwnerService;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.service.domain.Pet;
import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.repository.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    private OwnerService ownerService;

    @Override
    public List<PetDTO> findAllPets() {
        List<Pet> petList = petRepository.findAll();
        log.info("Fetching all pets");
        return petList
                .stream()
                .map(pet -> PetDTO.builder()
                        .species(pet.getSpecies())
                        .name(pet.getName())
                        .build())
                .toList();
    }

    @Override
    public PetResponseDTO createPet(Long id, PetDTO petDTO) {
        Owner owner = ownerService.findOwnerById(id);
        Pet pet = Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .practice_owner(owner)
                .build();
        petRepository.save(pet);
        owner.setPet(List.of(pet));
        log.info("Updating owner");
        ownerService.updateOwnerWithPet(owner, pet);
        log.info("saving pet");
        return PetResponseDTO.builder()
                .id(pet.getId())
                .species(petDTO.getSpecies())
                .name(pet.getName())
                .build();
    }

    @Override
    public PetResponseDTO updatePet(Long id, PetDTO petDTO) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Pet with id: " + id + " not found"));
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        log.info("updating pet");
        petRepository.save(pet);
        return PetResponseDTO.builder()
                .id(pet.getId())
                .species(petDTO.getSpecies())
                .name(pet.getName())
                .build();
    }

    @Override
    public void deletePet(Long id) {
        petRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Pet with id: " + id + " not found"));
        log.info("deleting pet");
        petRepository.deleteById(id);
    }
}
