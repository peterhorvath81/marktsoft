package com.marktsoft.practice.owner.service;

import com.marktsoft.practice.exception.NotFoundRequestException;
import com.marktsoft.practice.owner.controller.dto.OwnerResponseDTO;
import com.marktsoft.practice.owner.repository.domain.Owner;
import com.marktsoft.practice.owner.controller.dto.OwnerDTO;
import com.marktsoft.practice.owner.repository.OwnerRepository;
import com.marktsoft.practice.pet.repository.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    @Override
    public List<OwnerDTO> getAll(Sort sort) {
        List<Owner> ownerList = ownerRepository.findAll(sort);
//        update(1L, new OwnerDTO("Álémér","33333", "asd@email.com")); rákérdezni újra
        log.info("Fetching owners");
        return getOwnerDTOList(ownerList);
    }

    public List<OwnerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount) {
        Pageable pageable = PageRequest.of(pageNumber, pageCount, Sort.by(Sort.Direction.ASC, sortBy));
        List<Owner> ownerList = ownerRepository.findAll(pageable).get().toList();
        log.info("Fetching owners by pagination");
        return getOwnerDTOList(ownerList);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Owner with id: " + id + " not found"));
    }

    @Override
    public OwnerResponseDTO create(OwnerDTO ownerDTO) {
        Owner owner = getOwner(ownerDTO);
        log.info("Saving owner");
        ownerRepository.save(owner);
        return getOwnerResponseDTO(owner);
    }

    @Override
    @Transactional
    public OwnerResponseDTO update(Long id, OwnerDTO ownerDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The owner with the id " +id+ " not found"));
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        log.info("Updating owner");
        return getOwnerResponseDTO(owner);
    }

    @Override
    public void delete(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The owner with the id " +id+ " not found"));
        owner.setPets(null);
        log.info("deleting owner");
        ownerRepository.delete(owner);
    }

    @Override
    public void updateWithPet(Owner owner, Pet pet) {
        List<Pet> petList = new ArrayList<>();
        petList.add(pet);
        owner.setPets(petList);
        ownerRepository.save(owner);
    }

    private static List<OwnerDTO> getOwnerDTOList(List<Owner> ownerList) {
        return ownerList.stream().map(owner -> OwnerDTO.builder()
                .name(owner.getName())
                .phoneNumber(owner.getPhoneNumber())
                .email(owner.getEmail())
                .build()).toList();
    }

    private static OwnerResponseDTO getOwnerResponseDTO(Owner owner) {
        return OwnerResponseDTO.builder()
                .id(owner.getId())
                .name(owner.getName())
                .email(owner.getEmail())
                .build();
    }

    private static Owner getOwner(OwnerDTO ownerDTO) {
        return Owner.builder()
                .name(ownerDTO.getName())
                .email(ownerDTO.getEmail())
                .phoneNumber(ownerDTO.getPhoneNumber())
                .build();
    }
}
