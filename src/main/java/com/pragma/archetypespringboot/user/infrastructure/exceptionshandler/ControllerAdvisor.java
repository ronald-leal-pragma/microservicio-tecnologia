package com.pragma.archetypespringboot.user.infrastructure.exceptionshandler;

import com.pragma.archetypespringboot.user.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "message";
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlerUserNotFoundException(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, UserExceptionConstant.USER_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(MinorUserException.class)
    public ResponseEntity<Map<String,String>> handlerMinorUserException(MinorUserException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, UserExceptionConstant.USER_MINOR_MESSAGE));
    }
    @ExceptionHandler(InvalidUserParameterException.class)
    public ResponseEntity<Map<String,String>> handlerInvalidUserParameterException(InvalidUserParameterException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, UserExceptionConstant.INVALID_USER_PARAMETER_MESSAGE));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handlerUserAlreadyExistsException(UserAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, UserExceptionConstant.USER_ALREADY_EXISTS_MESSAGE));
    }
}
