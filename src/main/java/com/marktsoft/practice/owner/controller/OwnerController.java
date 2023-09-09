package com.marktsoft.practice.owner.controller;

import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.service.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;

    @GetMapping("owner")
    public List<OwnerDTO> getAllOwners() {
        return ownerService.getAllOwner();
    }

    @PostMapping("owner")
    public OwnerResponseDTO registerOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        return ownerService.createOwner(ownerDTO);
    }

    @PutMapping("owner/{id}")
    public OwnerResponseDTO updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDTO ownerDTO) {
        return ownerService.updateOwner(id, ownerDTO);
    }

    @DeleteMapping("owner/{id}")
    public String deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwner(id);
        return "Owner deleted with id: "+id;
    }
}
