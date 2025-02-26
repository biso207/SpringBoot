package com.example.provespringboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Gestisce l'eccezione UnexpectedRollbackException
    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<String> handleUnexpectedRollbackException(UnexpectedRollbackException ex) {
        // Log dell'errore
        System.err.println("LOG (" + (new Date()) + ") -> Rollback happened: " + ex.getMessage());

        // Restituisce un messaggio personalizzato con il codice di stato 409 (Conflict)
        String responseMessage = "{\"message\": \"Nodo creation failed due to conflict, transaction rolled back\"}";
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }
}
