package br.com.brasilprev.desafio.exception;

import org.springframework.http.HttpStatus;

public class IntegrationException extends RuntimeException {

    private HttpStatus status;

    public HttpStatus getStatus() {
        return this.status;
    }

    public IntegrationException(String errorMessage) {
        super(errorMessage);
    }

    public IntegrationException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
    }
    
}