package com.example.MercadoMutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatsResponse {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}
