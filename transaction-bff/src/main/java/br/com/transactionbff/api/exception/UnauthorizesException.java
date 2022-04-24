package br.com.transactionbff.api.exception;

public class UnauthorizesException extends RuntimeException {
    public UnauthorizesException(String message) {
        super(message);
    }
}
