package com.example.MercadoMutantes.service;

import com.example.MercadoMutantes.entity.DnaRecord;
import com.example.MercadoMutantes.repository.DnaRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

        @Mock
        private MutantDetector mutantDetector;  // Mock (simulado)

        @Mock
        private DnaRecordRepository dnaRecordRepository;  // Mock (simulado)

        @InjectMocks
        private MutantService mutantService;  // Clase bajo prueba (recibe mocks)
        private String[] mutantDna;
        private String[] humanDna;

        @Test
        @DisplayName("Debe analizar ADN mutante y guardarlo en DB")
        void testAnalyzeMutantDnaAndSave() {
            // ARRANGE (Preparar)
            when(dnaRecordRepository.findByDnaHash(anyString()))
                    .thenReturn(Optional.empty());  // No existe en BD
            when(mutantDetector.isMutant(mutantDna))
                    .thenReturn(true);  // Es mutante
            when(dnaRecordRepository.save(any(DnaRecord.class)))
                    .thenReturn(new DnaRecord());  // Guardado exitoso

            // ACT (Actuar)
            boolean result = mutantService.processDnaSequence(mutantDna);

            // ASSERT (Afirmar)
            assertTrue(result);

            // VERIFY (Verificar interacciones)
            verify(mutantDetector, times(1)).isMutant(mutantDna);
            verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
        }
        @Test
        @DisplayName("Debe analizar ADN humano y guardarlo en DB")
        void testAnalyzeHumanDnaAndSave() {
            when(dnaRecordRepository.findByDnaHash(anyString()))
                    .thenReturn(Optional.empty());
            when(mutantDetector.isMutant(humanDna))
                    .thenReturn(false);  // Es humano
            when(dnaRecordRepository.save(any(DnaRecord.class)))
                    .thenReturn(new DnaRecord());

            boolean result = mutantService.processDnaSequence(humanDna);

            assertFalse(result);
            verify(mutantDetector, times(1)).isMutant(humanDna);
            verify(dnaRecordRepository, times(1)).save(any(DnaRecord.class));
        }

    @Test
    @DisplayName("Debe generar hash consistente para el mismo ADN")
    void testConsistentHashGeneration() {
        when(dnaRecordRepository.findByDnaHash(anyString()))
                .thenReturn(Optional.empty());
        when(mutantDetector.isMutant(any()))
                .thenReturn(true);

        mutantService.processDnaSequence(mutantDna);
        mutantService.processDnaSequence(mutantDna);  // Mismo DNA otra vez

        // Debe buscar por el mismo hash ambas veces
        verify(dnaRecordRepository, times(2)).findByDnaHash(anyString());
    }
    @Test
    @DisplayName("Debe guardar registro con hash correcto")
    void testSavesRecordWithCorrectHash() {
        when(dnaRecordRepository.findByDnaHash(anyString()))
                .thenReturn(Optional.empty());
        when(mutantDetector.isMutant(mutantDna))
                .thenReturn(true);

        mutantService.processDnaSequence(mutantDna);

        verify(dnaRecordRepository).save(argThat(record ->
                record.getDnaHash() != null &&
                        record.getDnaHash().length() == 64 &&
                        record.getIsMutant()
        ));
    }
}
