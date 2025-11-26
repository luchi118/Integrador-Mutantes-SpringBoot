package com.example.MercadoMutantes.controller;

import com.example.MercadoMutantes.dto.DnaRequest;
import com.example.MercadoMutantes.dto.StatsResponse;
import com.example.MercadoMutantes.service.MutantService;
import com.example.MercadoMutantes.service.StatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutants")
@RequiredArgsConstructor
public class MutantController {

    @Autowired
    private final MutantService mutantService;
    @Autowired
    private final StatsService statsService;

    @PostMapping
    public ResponseEntity<String> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.processDnaSequence(dnaRequest.getDna());
        return isMutant
                ? ResponseEntity.ok("Mutante Detectado!")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es Mutante, es Humano!");
    }

    @GetMapping("/stats")
    public StatsResponse getStats() {
        return statsService.getStats();
    }



}
