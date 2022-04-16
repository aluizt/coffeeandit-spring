package br.com.transactionbff.api.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value = {FeignException.class})
    public ResponseEntity<CustomErrorResponse> eventoNotFoundException(FeignException e) {
        System.out.println(e.status());
        switch (e.status()){
            case 400:
                return new ResponseEntity<>(CustomErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .errorMsg(e.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
            case 404:
                return new ResponseEntity<>(CustomErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .errorMsg(e.getMessage())
                        .build(), HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(CustomErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errorMsg(e.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
