package com.ot6.proposta.config.exception.handler.client.dto;

public class ClientErrorResponse {

    private String message;

    public ClientErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
