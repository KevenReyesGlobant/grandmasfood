package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import jakarta.validation.ConstraintViolationException;
import org.grandmasfood.springcloud.orders.domain.exceptions.OrderNotFoundException;
import org.grandmasfood.springcloud.orders.domain.model.ErrorResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.grandmasfood.springcloud.clients.utils.ErrorCatalog.DUPLICATED_CLIENT_DATA;
import static org.grandmasfood.springcloud.orders.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ErrorResponseDTO handleOrderNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(ORDER_NOT_FOUND.getCode())
                .message(Collections.singletonList(ORDER_NOT_FOUND.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDTO handleDataIntegrityViolationException() {
        return ErrorResponseDTO.builder()
                .code(OVERFLOW_ORDER_DATA.getCode())
                .message(Collections.singletonList(OVERFLOW_ORDER_DATA.getMessage()))
                .timestamp(LocalDateTime.now())
                .exception(String.valueOf(ConstraintViolationException.class).split("validation.")[1])
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponseDTO.builder()
                .code(INVALID_ORDER.getCode())
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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleGenericError(Exception exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(Collections.singletonList(GENERIC_ERROR.getMessage()))
                .exception(exception.getClass().getName().split("resource.")[1])
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
}