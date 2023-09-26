package com.example.archetypespringboot.user.infrastructure.exceptionshandler;

import com.example.archetypespringboot.user.domain.exceptions.UserNotFoundException;
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
}
