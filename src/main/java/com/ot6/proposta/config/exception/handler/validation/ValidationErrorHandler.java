package com.ot6.proposta.config.exception.handler.validation;

import com.ot6.proposta.config.exception.handler.validation.dto.FormErrorResponse;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<FormErrorResponse> handleArgumentNotValid(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                    .stream()
                    .map(FormErrorResponse::new)
                    .collect(Collectors.toList());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotReadablePropertyException.class})
    public FormErrorResponse handleNotReadable(NotReadablePropertyException exception) {
        return new FormErrorResponse(exception.getPropertyName(), exception.getMessage());
    }
}
