package com.example.MercadoMutantes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="dna_records")
public class DnaRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_hash", nullable = false, length = 64, unique = true)
    private String dnaHash;

    @Column(name = "is_mutant", nullable = false)
    private Boolean isMutant;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
