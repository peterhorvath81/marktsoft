package com.marktsoft.practice.owner.controller;

import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.controller.dto.SortField;
import com.marktsoft.practice.owner.service.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;

    @GetMapping
    public List<OwnerDTO> getAll(@RequestParam(defaultValue = "NAME") SortField sortField,
                                 @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField.getDatabaseFieldName());
        return ownerService.getAll(sort);
    }

    @GetMapping("/pages")
    public List<OwnerDTO> getAllPaginated(@RequestParam String sortBy,
                                          @RequestParam("pageNumber") Integer pageNumber,
                                          @RequestParam("pageCount") Integer pageCount) {
        return ownerService.getAllPaginated(sortBy, pageNumber, pageCount);

    }

    @PostMapping
    public OwnerResponseDTO register(@Valid @RequestBody OwnerDTO ownerDTO) {
        return ownerService.create(ownerDTO);
    }

    @PutMapping("/{id}")
    public OwnerResponseDTO update(@PathVariable("id") Long id, @RequestBody OwnerDTO ownerDTO) {
        return ownerService.update(id, ownerDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        ownerService.delete(id);
        return "Owner deleted with id: "+id;
    }
}
