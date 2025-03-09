package com.ordermanagerwriter.application.domain.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
}
