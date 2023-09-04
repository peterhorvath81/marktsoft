package com.marktsoft.practice.service;

import com.marktsoft.practice.dto.OwnerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OwnerService {

    public List<OwnerDTO> getAllOwner();

    OwnerDTO createOwner(OwnerDTO ownerDTO);

    OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO);

    void deleteOwner(Long id);
}
