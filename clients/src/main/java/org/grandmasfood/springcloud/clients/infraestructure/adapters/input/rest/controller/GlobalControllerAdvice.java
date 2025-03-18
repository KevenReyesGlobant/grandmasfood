package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller;

import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.ErrorResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.grandmasfood.springcloud.clients.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ErrorResponseDTO handleOrderNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(CLIENT_NOT_FOUND.getCode())
                .message(Collections.singletonList(CLIENT_NOT_FOUND.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponseDTO handleClientNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(DUPLICATED_CLIENT_DATA.getCode())
                .message(Collections.singletonList(DUPLICATED_CLIENT_DATA.getMessage()))
                .timestamp(LocalDateTime.now())
                .exception(String.valueOf(DataIntegrityViolationException.class).split("dao.")[1])
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class})
    public ErrorResponseDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponseDTO.builder()
                .code(INVALID_CLIENT.getCode())
                .exception(MethodArgumentNotValidException.class.getName().split("bind.")[1])
                .message(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponseDTO handleNoResourceFoundException(NoResourceFoundException exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(Collections.singletonList(GENERIC_ERROR.getMessage()))
                .exception(NoResourceFoundException.class.getName().split("resource.")[1])
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponseDTO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(Collections.singletonList(GENERIC_ERROR.getMessage()))
                .exception(HttpRequestMethodNotSupportedException.class.getName().split("web.")[1])
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleGenericError(Exception exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(Collections.singletonList(GENERIC_ERROR.getMessage()))
                .exception(NoResourceFoundException.class.getName().split("resource.")[1])
                .timestamp(LocalDateTime.now())
                .build();
    }
}