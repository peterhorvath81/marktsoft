package com.marktsoft.practice.service;

import com.marktsoft.practice.domain.Owner;
import com.marktsoft.practice.domain.Pet;
import com.marktsoft.practice.dto.PetDTO;
import com.marktsoft.practice.repository.OwnerRepository;
import com.marktsoft.practice.repository.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    private OwnerRepository ownerRepository;

    @Override
    public List<PetDTO> findAllPets() {
        List<Pet> petList = petRepository.findAll();
        log.info("Fetching all pets");
        return petList
                .stream()
                .map(pet -> new PetDTO(pet.getSpecies(), pet.getName())).toList();
    }

    @Override
    public PetDTO createPet(Long id, PetDTO petDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner with id: " + id + "not found"));
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        pet.setOwner(owner);
        log.info("saving pet");
        petRepository.save(pet);
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
