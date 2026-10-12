package br.com.rm566844.cp2.sistemacursos.configurations;

import br.com.rm566844.cp2.sistemacursos.dto.ErrorResponse;
import br.com.rm566844.cp2.sistemacursos.dto.ValidationErrorResponse;
import br.com.rm566844.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ValidationErrorResponse.FieldErrorItem> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new ValidationErrorResponse.FieldErrorItem(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(status)
                .body(new ValidationErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Erro inesperado", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                        "Ocorreu um erro interno. Tente novamente mais tarde."));
    }
}
