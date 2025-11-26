package com.example.MercadoMutantes.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantDetectorTest {
    @Test
    @DisplayName("Debe detectar mutante con secuencias horizontal y diagonal")
    void testMutantWithHorizontalAndDiagonalSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",  // ← Horizontal: CCCC
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));  // ← Debe retornar true
    }
}