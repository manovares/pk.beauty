package br.com.matheustavares.cp2.sistemacursos.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        List<FieldErrorItem> errors
) {

    public record FieldErrorItem(String field, String message) {
    }
}
