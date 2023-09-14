package com.marktsoft.practice.pet.repository.domain;

import com.marktsoft.practice.owner.repository.domain.Owner;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "practice_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner practice_owner;


}
