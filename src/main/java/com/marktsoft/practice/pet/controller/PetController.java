package com.marktsoft.practice.pet.controller;

import com.marktsoft.practice.pet.controller.dto.PetDTO;
import com.marktsoft.practice.pet.controller.dto.PetResponseDTO;
import com.marktsoft.practice.pet.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @GetMapping
    public List<PetDTO> getAll() {
        return petService.findAll();
    }

    @PostMapping("/owner/{id}")
    public PetResponseDTO register(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return petService.create(id, petDTO);
    }

    @PutMapping("/{id}")
    public PetResponseDTO update(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        return petService.update(id, petDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        petService.delete(id);
        return "Pet deleted with id: " +id;
    }
}
