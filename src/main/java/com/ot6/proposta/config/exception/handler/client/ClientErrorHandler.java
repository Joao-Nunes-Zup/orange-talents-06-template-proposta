package com.ot6.proposta.config.exception.handler.client;

import com.ot6.proposta.config.exception.handler.client.dto.ClientErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class ClientErrorHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({FeignException.class})
    public ClientErrorResponse handleArgumentNotValid(FeignException exception) {
        return new ClientErrorResponse(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({HttpStatusCodeException.class})
    public ClientErrorResponse handleNotReadable(HttpStatusCodeException exception) {
        return new ClientErrorResponse(exception.getMessage());
    }
}
