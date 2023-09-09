package com.marktsoft.practice.owner.service.domain;

import com.marktsoft.practice.pet.service.domain.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "practice_owner")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    @NotBlank(message = "the e-mail address can not be null")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "practice_owner")
    private List<Pet> pet;
}
