package com.example.MercadoMutantes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if(dna == null||dna.length == 0) return false;

        final int n = dna.length;
        Pattern pattern = Pattern.compile("^[ATCG]+$");

        for (String row : dna) {
            //valida que la matriz sea NxN
            if (row == null || row.length() != n) {
                return false;
            }
            //Valida que los caracteres sean A T C G
                if (!pattern.matcher(row).matches()){
                    return false;
            }
        }
        return true;
    }
}
