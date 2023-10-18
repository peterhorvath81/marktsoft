package com.marktsoft.practice.customer.repository.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column( nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(nullable = false, length = 45)
    private String lastName;

    @Size(max = 50)
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Boolean activebool = false;

    @NotNull
    @Column(nullable = false)
    private LocalDate createDate;

    private Instant lastUpdate;

    private Integer active;

}