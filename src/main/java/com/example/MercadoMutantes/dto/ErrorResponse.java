package com.example.MercadoMutantes.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    final LocalDateTime timestamp;
    final int status;
    final String error;
    final List<String> detalles;

    public static ErrorResponse of(int status, String error, List<String> detalles){
        return new ErrorResponse(LocalDateTime.now(), status, error, detalles);
    }
    public static ErrorResponse simple(int status, String error, String detalle){
        return new ErrorResponse(LocalDateTime.now(), status, error, List.of(detalle));
    }
}
