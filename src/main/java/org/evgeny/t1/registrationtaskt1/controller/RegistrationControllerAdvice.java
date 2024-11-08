package org.evgeny.t1.registrationtaskt1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.evgeny.t1.registrationtaskt1.service.exception.InternalServiceException;
import org.evgeny.t1.registrationtaskt1.service.exception.InvalidInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class RegistrationControllerAdvice {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, List<String>>> handleJsonMappingException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(Map.of("errors",List.of( ex.getMessage())), HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,List<String>>> handleException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        Map<String,List<String>> errorsMap = new HashMap<>();
        errorsMap.put("errors", errors);
        return ResponseEntity.badRequest().body(errorsMap);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidInputData.class)
    public ResponseEntity<Map<String,List<String>>> handleException(InvalidInputData ex){
        return new ResponseEntity<>(Map.of("errors",List.of("Processing data error")), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServiceException.class)
    public ResponseEntity<Map<String,List<String>>> handleException(InternalServiceException ex){
        return new ResponseEntity<>(Map.of("errors",List.of("Processing data error")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
