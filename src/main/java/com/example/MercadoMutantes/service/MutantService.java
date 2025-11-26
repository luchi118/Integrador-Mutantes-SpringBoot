package com.example.MercadoMutantes.service;

import com.example.MercadoMutantes.entity.DnaRecord;
import com.example.MercadoMutantes.repository.DnaRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MutantService {

    private final DnaRecordRepository dnaRecordRepository;
    private final MutantDetector mutantDetector;

    //Algoritmo para crear el dnaHash
    private String calculateDnaHash(String[] dna) throws NoSuchAlgorithmException {
        // Concatena el array de ADN en una sola cadena para hashear
        String dnaSequenceString = Arrays.toString(dna);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(dnaSequenceString.getBytes());

        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    //Crear el record, creando el dnahash
    @Transactional
    public boolean processDnaSequence(String[] dna) {
        // 1. Calcular el Hash SHA-256 del DNA
        String hashedDna;
        try {
            hashedDna = calculateDnaHash(dna);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Algoritmo SHA-256 no disponible.", e);
        }
        // 2. Buscar en BD si ya fue analizado
        Optional<DnaRecord> cachedRecord = dnaRecordRepository.findByDnaHash(hashedDna);
        // 3. Si existe → Retorna resultado cacheado

        if (cachedRecord.isPresent()) {
            System.out.println("Resultado encontrado en caché para hash: " + hashedDna);
            return cachedRecord.get().getIsMutant();
        }
        // 4. Si NO existe → Llamar a MutantDetector y guardar el resultado
        // Llama al detector de mutantes
        boolean isMutant = mutantDetector.isMutant(dna);

        // Crea y guarda el nuevo registro
        //DnaRecord newRecord = new DnaRecord(hashedDna, isMutant, LocalDateTime.now());
        //dnaRecordRepository.save(newRecord);

        DnaRecord record = new DnaRecord();
        record.setDnaHash(hashedDna);
        record.setIsMutant(isMutant);
        record.setCreatedAt(LocalDateTime.now());
        dnaRecordRepository.save(record);

        System.out.println("Nuevo análisis realizado y guardado para hash: " + hashedDna);
        return isMutant;
    }

    public Optional<DnaRecord> getDnaRecord(String[] dnaSequence) {
        String hashedDna;
        try {
            hashedDna = calculateDnaHash(dnaSequence);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Algoritmo SHA-256 no disponible", e);
        }
        // Busca el registro usando el hash
        return dnaRecordRepository.findByDnaHash(hashedDna);
    }
}


