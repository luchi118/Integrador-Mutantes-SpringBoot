package com.example.MercadoMutantes.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantDetectorTest {

    //TEST UNITARIOS
    //Casos Mutantes
    //Horizontal + Diagonal
    @Test
    @DisplayName("Debe detectar mutante con secuencias horizontal y diagonal")
    void testMutantWithHorizontalAndDiagonalSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    //Verticales
    @Test
    @DisplayName("Debe detectar mutante con 2 secuencias verticales")
    void testMutantWithVerticalSequences() {
        String[] dna = {
                "ATGCGA",
                "ATGTGC",
                "ATATGT",
                "AGAAGG",
                "CCCATA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));  // ← Debe retornar true
    }
    //Multiples Horizontales
    @Test
    @DisplayName("Debe detectar mutante con secuencias horizontales")
    void testMutantWithMultipleHorizontalSequences() {
        String[] dna = {
                "AAAAGA",
                "CGGTAC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    //Diagonales ascendentes y descendentes
    @Test
    @DisplayName("Debe detectar mutante con secuencias diagonales")
    void testMutantWithBothDiagonalSequences() {
        String[] dna = {
                "ATGCGA",
                "CACTAC",
                "TCATGT",
                "CGAAGG",
                "CGCCTA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    //Matriz Grande
    @Test
    @DisplayName("Debe detectar mutante en matriz 10x10")
    void testMutantWithLargeDna() {
        String[] dna = {
                "ATGCGTACGA",
                "CAGTAACCCC",
                "TTATGTTGAC",
                "AGAAGCTACG",
                "CACGGACATA",
                "TCACGTACTG",
                "TTATGTTGAC",
                "AGAAGCTACG",
                "ATGCGTACGA",
                "CACGGACATA"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    //Todo es el mismo caracter
    @Test
    @DisplayName("Debe detectar mutante con todo igual")
    void testMutantWithAllSameCharacter() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    //CASOS HUMANOS
    //Solo una secuencia
    @Test
    @DisplayName("Detecta humano con solo una secuencia")
    void testNotMutantWithOnlyOneSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }
    //Sin secuencias
    @Test
    @DisplayName("Detecta humano sin secuencias")
    void testNotMutantWithNoSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAACG",
                "GCGTCA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }
    //Matriz 4x4 sin secuencias
    @Test
    @DisplayName("Matriz 4x4 sin secuencias")
    void testNotMutantSmallDna() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }
    //Matriz nula
    @Test
    @DisplayName("Matriz nula")
    void testNotMutantWithNullDna() {
        String[] dna = null;
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Array vacío
    @Test
    @DisplayName("Matriz vacía")
    void testNotMutantWithEmptyDna() {
        String[] dna = {};
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Matriz no cuadrada (4x5)
    @Test
    @DisplayName("Matriz no cuadrada")
    void testNotMutantWithNonSquareDna() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC",
                "GCTA"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Carácter inválido 'X'
    @Test
    @DisplayName("Caracter inválido")
    void testNotMutantWithInvalidCharacters() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTXTGT",
                "AGAACG",
                "GCGTCA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Fila null
    @Test
    @DisplayName("Matriz con fila nula")
    void testNotMutantWithNullRow() {
        String[] dna = {
                "ATGCGA",
                null,
                "TTATGT",
                "AGAACG",
                "GCGTCA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Matriz muy pequeña (3x3)
    @Test
    @DisplayName("Matriz pequeña")
    void testNotMutantWithTooSmallDna() {
        String[] dna = {
                "ATG",
                "CAG",
                "TTA"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }
    // Secuencias de longitud 5 (no deben contar)
    @Test
    void testNotMutantWithSequenceLongerThanFour() {
        String[] dna = {
                "AAAAA", // 5 caracteres iguales → debería ser ignorado
                "CAGTG",
                "TTATG",
                "AGACC",
                "GCGTC"
        };
        MutantDetector detector = new MutantDetector();
        assertFalse(detector.isMutant(dna));
    }

    // Diagonal en esquina
    @Test
    void testMutantDiagonalInCorner() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTAAGT",
                "AGAAAG",
                "CCCATA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
    }
    @Test
    @DisplayName("Debe usar early termination para eficiencia")
    void testEarlyTermination() {
        String[] dna = {
                "AAAAGA",  // Secuencia 1
                "AAAAGC",  // Secuencia 2
                "TTATGT",  // Ya no se revisa (early termination)
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        MutantDetector detector = new MutantDetector();
        assertTrue(detector.isMutant(dna));
        long startTime = System.nanoTime();
        boolean result = detector.isMutant(dna);
        long endTime = System.nanoTime();

        assertTrue(result);
        assertTrue((endTime - startTime) < 10_000_000); // < 10ms
    }
}