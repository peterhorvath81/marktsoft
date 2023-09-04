package com.marktsoft.practice.pet.service;

import com.marktsoft.practice.owner.domain.Owner;
import com.marktsoft.practice.owner.service.OwnerService;
import com.marktsoft.practice.pet.domain.Pet;
import com.marktsoft.practice.pet.dto.PetDTO;
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
    public PetDTO createPet(Long id, PetDTO petDTO) {
        Owner owner = ownerService.findOwnerById(id);
        Pet pet = Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .owner(owner)
                .build();
        petRepository.save(pet);
        owner.setPet(List.of(pet));
        log.info("Updating owner");
        ownerService.updateOwnerWithPet(owner, pet);
        log.info("saving pet");
        return petDTO;
    }

    @Override
    public PetDTO updatePet(Long id, PetDTO petDTO) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet with id: " + id + "not found"));
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        log.info("updating pet");
        petRepository.save(pet);
        return petDTO;
    }

    @Override
    public void deletePet(Long id) {
        log.info("deleting pet");
        petRepository.deleteById(id);
    }
}
