package com.accenture.tigital.libraries.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.accenture.tigital.libraries.enums.ErrorScope;
import com.accenture.tigital.libraries.exceptions.RestException;
import com.accenture.tigital.libraries.typings.ErrorResponse;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        if (ex instanceof RestException) {
            RestException bs280Exception = (RestException) ex;
            ErrorResponse errorResponse = new ErrorResponse(bs280Exception.getMessage(), bs280Exception.getCode(), bs280Exception.getScope());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        ErrorResponse errorResponse = new ErrorResponse("Error: " + ex.getMessage(), 500, ErrorScope.SERVER);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
