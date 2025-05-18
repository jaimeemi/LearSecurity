package com.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> manejarError(MethodArgumentNotValidException ex) {
        Map<String,String> respuestaError = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            respuestaError.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(respuestaError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorGenerico(Exception ex) {
        return new ResponseEntity<>("Error Interno "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
