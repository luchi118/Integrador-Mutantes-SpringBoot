package com.example.MercadoMutantes.dto;

import com.example.MercadoMutantes.validation.ValidDnaSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DnaRequest {
    @NotNull(message= "No se permite DNA nulo")
    @NotEmpty(message = "No se permite DNA vacío")
    @ValidDnaSequence
    private String[] dna;
}
