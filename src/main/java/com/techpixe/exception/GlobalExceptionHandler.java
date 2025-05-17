package com.techpixe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

 

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        // Get the status and message from the exception object
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String message = ex.getReason();

        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        
        return new ResponseEntity<>(response, status);
    }



    

    //for getting 400 bad request when we miss the parameter from the data
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getParameterName() + " missing");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    
 //for handling validationErrors  
// // Handle validation errors for request parameters and request body
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        FieldError error = ex.getBindingResult().getFieldErrors().get(0); // Get the first error
//        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
//        errors.put("error", "Bad Request");
//        errors.put("message", error.getDefaultMessage());
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
    
  //for handling validationErrors    
 // Handle validation errors for persistence layer (e.g., JPA constraints)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", "Bad Request");
        errors.put("message", errorMessage);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

	
	
    
    
    
}





