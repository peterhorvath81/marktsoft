package com.marktsoft.practice.owner.controller;

import com.marktsoft.practice.owner.dto.OwnerDTO;
import com.marktsoft.practice.owner.service.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;

    @GetMapping("owner")
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwner(), HttpStatus.OK);
    }

    @PostMapping("owner")
    public ResponseEntity<OwnerDTO> registerOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        return new ResponseEntity<>(ownerService.createOwner(ownerDTO), HttpStatus.OK);
    }

    @PutMapping("owner/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDTO ownerDTO) {
        return new ResponseEntity<>(ownerService.updateOwner(id, ownerDTO), HttpStatus.OK);
    }

    @DeleteMapping("owner/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>("Owner deleted with id: "+id, HttpStatus.OK);
    }
}
