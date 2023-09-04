package com.marktsoft.practice.service;

import com.marktsoft.practice.domain.Owner;
import com.marktsoft.practice.dto.OwnerDTO;
import com.marktsoft.practice.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    @Override
    public List<OwnerDTO> getAllOwner() {
        List<Owner> ownerList = ownerRepository.findAll();
        log.info("Fetching owners");
        return ownerList
                .stream()
                .map(owner -> new OwnerDTO(owner.getName(), owner.getPhoneNumber(),owner.getEmail())).toList();
    }

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        log.info("Saving owner");
        ownerRepository.save(owner);

        return ownerDTO;
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The owner with the id " +id+ "not found"));
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        log.info("Updating owner");
        ownerRepository.save(owner);
        return ownerDTO;
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The owner with the id " +id+ "not found"));
        owner.setPet(null);
        log.info("deleting owner");
        ownerRepository.delete(owner);
    }
}
