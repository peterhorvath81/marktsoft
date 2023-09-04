package com.marktsoft.practice.pet.domain;

import com.marktsoft.practice.owner.domain.Owner;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;


}
