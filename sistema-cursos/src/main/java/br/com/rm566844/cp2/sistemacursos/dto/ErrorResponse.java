package br.com.rm566844.cp2.sistemacursos.dto;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
}
