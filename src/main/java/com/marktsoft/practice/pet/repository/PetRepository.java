package com.marktsoft.practice.pet.repository;

import com.marktsoft.practice.pet.repository.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
