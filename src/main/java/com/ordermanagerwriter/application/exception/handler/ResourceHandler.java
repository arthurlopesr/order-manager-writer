package com.ordermanagerwriter.application.exception.handler;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException badRequestException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder()
                .message(badRequestException.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build()
        );
    }
}
