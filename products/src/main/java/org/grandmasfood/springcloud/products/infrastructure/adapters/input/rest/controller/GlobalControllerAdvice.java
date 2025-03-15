package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller;


import org.grandmasfood.springcloud.products.domain.exceptions.ProductNotFoundException;
import org.grandmasfood.springcloud.products.domain.model.ErrorResponseDTO;
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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.grandmasfood.springcloud.products.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class, NullPointerException.class})
    public ErrorResponseDTO handleNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(PRODUCT_NOT_FOUND.getCode())
                .message(PRODUCT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(String.valueOf(NullPointerException.class).split("lang.")[1])
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponseDTO handleClientNotFoundException() {
        return ErrorResponseDTO.builder()
                .code(DUPLICATED_PRODUCT_DATA.getCode())
                .message(DUPLICATED_PRODUCT_DATA.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(String.valueOf(DataIntegrityViolationException.class).split("dao.")[1])
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class, NoResourceFoundException.class})
    public ErrorResponseDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        return ErrorResponseDTO.builder()
                .code(INVALID_PRODUCT.getCode())
                .message(INVALID_PRODUCT.getMessage())
                .exception(MethodArgumentNotValidException.class.getName().split("bind.")[1])
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponseDTO notFoundException(
            NoHandlerFoundException exception) {
        return ErrorResponseDTO.builder()
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .exception(NoHandlerFoundException.class.getName().split("servlet.")[1])
                .timestamp(LocalDateTime.now())
                .build();
    }
}
