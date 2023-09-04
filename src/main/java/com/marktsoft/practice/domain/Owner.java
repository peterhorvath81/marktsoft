package com.marktsoft.practice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="owner_id")
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pet;
}
