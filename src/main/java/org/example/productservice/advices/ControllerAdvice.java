package org.example.productservice.advices;

import org.example.productservice.dto.ErrorDto;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.exception.RunTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handelProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());
        ResponseEntity<ErrorDto> response = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(RunTimeException.class)
    public  ResponseEntity<ErrorDto> cusstomRuntimeExceptionHandler(RunTimeException runTimeException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(runTimeException.getMessage());
        ResponseEntity<ErrorDto> response = new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return  response;

    }
    @ExceptionHandler(NoSuchElementException.class)
    public  ResponseEntity<ErrorDto> customNoSuchElementFound(NoSuchElementException noSuchElementException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(noSuchElementException.getMessage());
        ResponseEntity<ErrorDto> response = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return  response;

    }
}
