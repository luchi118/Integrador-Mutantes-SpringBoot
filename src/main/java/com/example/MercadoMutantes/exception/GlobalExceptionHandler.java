package com.example.MercadoMutantes.exception;

import com.example.MercadoMutantes.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {
            List<String> detalles = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField()+": "+error.getDefaultMessage())
                    .toList();
            ErrorResponse errorDTO = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "Error de validación", detalles);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(HashCalculationException.class)
    public ResponseEntity<ErrorResponse> handleDnaHashError(
            HashCalculationException ex) {
        String detalle = "Falló el cálculo de Hash";
        ErrorResponse errorHash = ErrorResponse.simple(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error de Hash:", detalle);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorHash);
        // Manejar error custom
    }
}
