package com.planilla_DAWI.cibertec.Config;

import com.planilla_DAWI.cibertec.Dto.ApiErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepcionHandlerGlobal { 
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setMensaje(ex.getMessage());
        error.setCodigo(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setMensaje(ex.getMessage());
        error.setCodigo(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
