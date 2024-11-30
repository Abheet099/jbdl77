package com.example.crud_app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.crud_app.exceptions.BadPersonRequestException;
import com.example.crud_app.model.response.ErrorResponse;

@Configuration
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        List<String> errors = fieldErrors.stream().map(x -> x.getDefaultMessage()).toList();
        // custom logic

        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "ERR_405");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BadPersonRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadPersonRequestException(BadPersonRequestException ex){
        List<String> customMessage = new ArrayList<>();
        customMessage.add("Person with provided id doesn't exist");

        ErrorResponse errorResponse = new ErrorResponse(customMessage, HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(),
                "ERR_401");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
