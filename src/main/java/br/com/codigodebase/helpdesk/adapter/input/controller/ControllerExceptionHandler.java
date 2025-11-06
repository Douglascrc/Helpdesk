package br.com.codigodebase.helpdesk.adapter.input.controller;



import br.com.codigodebase.helpdesk.infrastructure.exception.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e) {
    	StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}