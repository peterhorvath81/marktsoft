package com.marktsoft.practice.owner.domain;

import com.marktsoft.practice.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "owner")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pet;
}
