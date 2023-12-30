package com.wishfy.ems.exceptions;

import com.wishfy.ems.helper.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message = ex.getMessage();



        ApiResponseMessage responseMessage=new ApiResponseMessage(HttpStatus.NOT_FOUND, message,false);

        return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);

    }
}


