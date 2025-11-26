package com.example.MercadoMutantes.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@NoArgsConstructor

@Component
public class MutantDetector {
    private final static int SEQUENCE_LENGTH = 4;
    Set<Character> VALID_BASES = Set.of('A','T','C','G');

    public boolean isMutant(String[] dna) {
        //Revisar si es una entrada válida (que no sea nula)
        if (dna == null || dna.length == 0) {
            System.out.println("la matriz no puede ser nula");
            return false;
        }
        final int n = dna.length;
        //que sea cuadrada
        for (String row : dna) {
            if (row == null || row.length() != n) {
                System.out.println("la matriz debe ser cuadrada");
                return false;
            }
            //que sea de 4 elementos o más
            if (n <= SEQUENCE_LENGTH - 1) {
                System.out.println("la matriz debe ser de 4x4 mínimo");
                return false;
            }
            for (char c : row.toCharArray()) { //que solo contenga caracteres válidos
                if (!VALID_BASES.contains(c)) {
                    System.out.println("la matriz contiene caracteres no válidos");
                    return false;
                }
            }
        }

        // Convertir String[] a char[][]
        char[][] matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        //Contador
        int sequenceCount = 0;

        // 4. PARA cada posición (fila, columna) en la matriz:
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // Verificar horizontal
                if (c <= n - SEQUENCE_LENGTH) {
                    if (checkHorizontal(matrix, r, c)) {
                        System.out.println("encontrada secuencia horizontal");
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }
                // Verificar para vertical
                if (r <= n - SEQUENCE_LENGTH) {
                    if (checkVertical(matrix, r, c)) {
                        System.out.println("encontrada secuencia vertical");
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }
                //Verificar para diagonal
                if (r <= n - SEQUENCE_LENGTH && c<= n - SEQUENCE_LENGTH) {
                    if (checkDiagonal(matrix, r, c)) {
                        System.out.println("encontrada secuencia diagonal descendiente");
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }
                // d. SI hay espacio para diagonal ↗ → buscar 4 iguales (Arriba-Derecha)
                // Se busca hacia arriba desde la posición actual, así que r debe ser al menos 3
                if (r >= SEQUENCE_LENGTH - 1 && c <= n - SEQUENCE_LENGTH) {
                    if (checkAntiDiagonal(matrix, r, c)) { // r-1, c+1; r-2, c+2; r-3, c+3
                        sequenceCount++;
                        System.out.println("encontrada secuencia diagonal ascendiente");
                        if (sequenceCount > 1) return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean checkHorizontal ( char[][] matrix, int row, int col){
        final char base = matrix[row][col];
        return matrix[row][col + 1] == base &&
                matrix[row][col + 2] == base &&
                matrix[row][col + 3] == base;
    }
    private boolean checkVertical (char[][] matrix, int row, int col){
        final char base = matrix[row][col];
        return matrix[row+1][col] == base &&
                matrix[row+2][col] == base &&
                matrix[row+3][col] == base;
    }
    private boolean checkDiagonal (char[][] matrix, int row, int col){
        final char base = matrix[row][col];
        return matrix[row+1][col+1] == base &&
                matrix[row+2][col+2] == base &&
                matrix[row+3][col+3] ==base;
    }
    private boolean checkAntiDiagonal(char[][] matrix, int row, int col){
        final char base = matrix[row][col];
        return matrix[row-1][col+1] == base &&
                matrix[row-2][col+2] == base &&
                matrix[row-3][col+3] ==base;
    }
}