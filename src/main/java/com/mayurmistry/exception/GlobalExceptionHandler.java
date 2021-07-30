package com.mayurmistry.exception;

import com.mayurmistry.model.ApiErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final HashMap<String, String> constraintMessage = new HashMap<>();

    GlobalExceptionHandler() {
        constraintMessage.put("users.email_UN", "Email id already exists");
        constraintMessage.put("users.username_UN", "username already exists");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception, WebRequest request) {

        if (exception.getCause() instanceof ConstraintViolationException) {
            String constraintName = ((ConstraintViolationException) exception.getCause()).getConstraintName();
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setError("CONSTRAINT_VIOLATION");
            apiErrorResponse.setMessage(constraintMessage.get(constraintName));
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.OK);
        }

        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setError("ISE");
        errorResponse.setMessage("Some error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
