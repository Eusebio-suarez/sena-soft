package com.senasoft.tikets.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senasoft.tikets.utils.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionImpl.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(ExceptionImpl exception){

        return ResponseEntity.status(exception.getStatus())
            .body(ApiResponse.builder()
                .success(false)
                .message(exception.getMessage())
                .data("error:" +exception.getStatus())
                .build()
            );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(MethodArgumentNotValidException exception){

        return ResponseEntity.status(exception.getStatusCode())
            .body(ApiResponse.builder()
                .success(false)
                .message("faltan campos o formato incorrecto.")
                .data(null)
                .build()
            );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(DataIntegrityViolationException exception){

        String message = "Error en los datos ingresados";

        if (exception.getMessage().contains("Duplicate entry")) {
            message = "El correo electrónico ya está registrado";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.builder()
                .success(false)
                .message(message)
                .data(null)
                .build()
            );
    }
}