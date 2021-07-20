package com.ot6.proposta.shared.validation.handler.dto;

import org.springframework.validation.FieldError;

public class FormErrorResponse {

    private String field;
    private String message;

    public FormErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public FormErrorResponse(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
