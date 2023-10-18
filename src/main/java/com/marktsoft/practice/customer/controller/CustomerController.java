package com.marktsoft.practice.customer.controller;

import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.SortField;
import com.marktsoft.practice.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable("id") Integer id) {
        return customerService.findById(id);
    }

    @GetMapping("/pages")
    public List<CustomerDTO> getAllPaginated(@RequestParam String sortBy,
                                             @RequestParam("pageNumber") Integer pageNumber,
                                             @RequestParam("pageCount") Integer pageCount) {
        return customerService.getAllPaginated(sortBy, pageNumber, pageCount);

    }

    @PostMapping
    public CustomerResponseDTO register(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.create(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable("id") Integer id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerService.delete(id);
        return "Customer deleted with id: "+id;
    }
}
