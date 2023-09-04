package com.marktsoft.practice.pet.controller;

import com.marktsoft.practice.pet.dto.PetDTO;
import com.marktsoft.practice.pet.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @GetMapping("pet")
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return new ResponseEntity<>(petService.findAllPets(), HttpStatus.OK);
    }

    @PostMapping("owner/{id}/pet")
    public ResponseEntity<PetDTO> registerPet(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return new ResponseEntity<>(petService.createPet(id, petDTO), HttpStatus.OK);
    }

    @PutMapping("pet/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return new ResponseEntity<>(petService.updatePet(id, petDTO), HttpStatus.OK);
    }

    @DeleteMapping("pet/{id}")
    public ResponseEntity<String> deletePet(@PathVariable("id") Long id) {
        petService.deletePet(id);
        return new ResponseEntity<>("Pet deleted with id: " +id, HttpStatus.OK);
    }
}
