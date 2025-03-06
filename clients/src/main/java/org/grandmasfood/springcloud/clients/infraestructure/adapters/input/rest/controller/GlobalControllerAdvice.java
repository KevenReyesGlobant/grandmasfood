package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller;

import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response.ErrorResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.grandmasfood.springcloud.clients.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ErrorResponseDTO handleStudentNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(CLIENT_NOT_FOUND.getCode())
                .message(CLIENT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponseDTO.builder()
                .code(INVALID_CLIENT.getCode())
                .message(INVALID_CLIENT.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleGenericError(Exception exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
