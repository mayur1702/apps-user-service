package com.mayurmistry.exception;

import com.mayurmistry.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final HashMap<String, String> constraintMessage = new HashMap<>();
    public static final HashMap<String, String> constraintField = new HashMap<>();

    GlobalExceptionHandler() {
        // email
        constraintMessage.put("users.email_UN", "Email id already exists");
        constraintField.put("users.email_UN", "email");

        // username
        constraintMessage.put("users.username_UN", "username already exists");
        constraintField.put("users.username_UN", "username");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception, WebRequest request) {

        if (exception.getCause() instanceof ConstraintViolationException) {
            String constraintName = ((ConstraintViolationException) exception.getCause()).getConstraintName();
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setError("CONSTRAINT_VIOLATION");
            apiErrorResponse.setField(constraintField.get(constraintName));
            apiErrorResponse.setMessage(constraintMessage.get(constraintName));
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.OK);
        }

//        if (exception.getCause() instanceof MethodArgumentNotValidException) {
//            System.out.println("mayur");
//        }

        log.error(String.valueOf(exception.getCause()));
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setError("ISE");
        errorResponse.setMessage("Some error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setError("USER_NOT_FOUND");
        apiErrorResponse.setMessage("User not found in system, please register");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = CredentialsDoNotMatch.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(CredentialsDoNotMatch credentialsDoNotMatch) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setError("INVALID_CREDENTIALS");
        apiErrorResponse.setMessage("Invalid credentials provided, please try again");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = CustomBindingException.class)
    public ResponseEntity<List<ApiErrorResponse>> handleBindingException(CustomBindingException customBindingException) {
        BindingResult bindingResult = customBindingException.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<ApiErrorResponse> apiErrorResponseList = new ArrayList<>();
        for (FieldError e: errors) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setError("BAD_REQUEST");
            apiErrorResponse.setField(e.getField());
            apiErrorResponse.setMessage(e.getDefaultMessage());
            apiErrorResponseList.add(apiErrorResponse);
        }
        return new ResponseEntity<>(apiErrorResponseList, HttpStatus.BAD_REQUEST);
    }

}
