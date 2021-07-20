package com.ot6.proposta.shared.validation.handler;

import com.ot6.proposta.shared.validation.handler.dto.FormErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<FormErrorResponse> handle(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors()
                .stream()
                .map(FormErrorResponse::new)
                .collect(Collectors.toList());
    }
}
