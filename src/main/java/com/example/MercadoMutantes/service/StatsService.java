package com.example.MercadoMutantes.service;

import com.example.MercadoMutantes.dto.StatsResponse;
import com.example.MercadoMutantes.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatsService {
    @Autowired
    private DnaRecordRepository dnaRecordRepository;

    public StatsResponse getStats(){
        long countMutantDna = dnaRecordRepository.countByIsMutant(true);
        long countHumanDna = dnaRecordRepository.countByIsMutant(false);

        double ratio = countHumanDna == 0 ? 0.0 : (double) countMutantDna/countHumanDna;

        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}
