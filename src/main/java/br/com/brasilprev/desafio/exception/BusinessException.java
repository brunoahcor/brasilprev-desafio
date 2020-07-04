package br.com.brasilprev.desafio.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private HttpStatus status;

    public HttpStatus getStatus() {
        return this.status;
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
    }
    
}