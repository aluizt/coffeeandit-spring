package br.com.transactionbff.api.exception;

import feign.FeignException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseExceptionHandler {

    public ResponseEntity<CustomErrorResponse> unauthorized(UnauthorizesException e) {
        return response(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {FeignException.class})
    public ResponseEntity<CustomErrorResponse> eventoNotFoundException(FeignException e) {
        System.out.println(e.status());
        switch (e.status()) {
            case 400:
                return response(e.getMessage(), HttpStatus.BAD_REQUEST);
            case 404:
                return response(e.getMessage(), HttpStatus.NOT_FOUND);
            default:
                return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<CustomErrorResponse> response(String msg, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .httpStatus(httpStatus.value())
                .errorMsg(msg)
                .build());
    }

}
