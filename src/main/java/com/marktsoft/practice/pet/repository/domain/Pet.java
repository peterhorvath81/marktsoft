package com.marktsoft.practice.pet.repository.domain;

import com.marktsoft.practice.owner.repository.domain.Owner;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "p_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;

    private String name;

    @ManyToMany(mappedBy = "petList")
    private List<Owner> ownerList;
}
