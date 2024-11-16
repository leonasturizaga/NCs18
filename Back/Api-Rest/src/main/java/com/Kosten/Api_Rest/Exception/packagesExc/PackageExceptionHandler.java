package com.Kosten.Api_Rest.Exception.packagesExc;

import com.Kosten.Api_Rest.Exception.ApplicationExceptionResponse;
import com.Kosten.Api_Rest.Exception.ExceptionUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(1)
public class PackageExceptionHandler {

    @ExceptionHandler(PackageNotFoundException.class)
    public ResponseEntity<ApplicationExceptionResponse> userNotFoundException(PackageNotFoundException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>(Map.of(ex.getClass().getSimpleName(), ex.getMessage()));
        ApplicationExceptionResponse errorResponse = ExceptionUtils.createResponse(HttpStatus.NOT_FOUND, req, errors);

        return ResponseEntity.status(404).body(errorResponse);
    }

}
