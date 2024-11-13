package com.tapiwa.book.social.exceptions.controllerAdvice;

import com.tapiwa.book.social.custom.HttpResponse;
import com.tapiwa.book.social.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionControllerAdvice {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<HttpResponse> handleAppException() {

        HttpResponse response = HttpResponse.builder()
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(400).body(response);
    }
}
