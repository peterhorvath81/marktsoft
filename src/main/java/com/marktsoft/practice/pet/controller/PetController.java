package com.marktsoft.practice.pet.controller;

import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @GetMapping("pet")
    public List<PetDTO> getAllPets() {
        return petService.findAllPets();
    }

    @PostMapping("owner/{id}/pet")
    public PetResponseDTO registerPet(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return petService.createPet(id, petDTO);
    }

    @PutMapping("pet/{id}")
    public PetResponseDTO updatePet(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return petService.updatePet(id, petDTO);
    }

    @DeleteMapping("pet/{id}")
    public String deletePet(@PathVariable("id") Long id) {
        petService.deletePet(id);
        return "Pet deleted with id: " +id;
    }
}
