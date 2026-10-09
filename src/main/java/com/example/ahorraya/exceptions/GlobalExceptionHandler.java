package com.example.ahorraya.exceptions;


import com.example.ahorraya.dtos.ExceptionMessaggeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ExceptionMessaggeDTO> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return new ResponseEntity<>(new ExceptionMessaggeDTO(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionMessaggeDTO> manejarRuntime(RuntimeException ex) {
        return new ResponseEntity<>(new ExceptionMessaggeDTO(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

}
