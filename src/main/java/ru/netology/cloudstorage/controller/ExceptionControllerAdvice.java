package ru.netology.cloudstorage.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.cloudstorage.exception.InvalidCredentials;
import ru.netology.cloudstorage.exception.UnauthorizedUser;
import ru.netology.cloudstorage.model.ExceptionResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCredentials (InvalidCredentials e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorizedUser (UnauthorizedUser e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getMessage(), 401));
    }
}
