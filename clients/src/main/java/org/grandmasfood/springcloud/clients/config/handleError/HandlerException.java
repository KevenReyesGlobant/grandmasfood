package org.grandmasfood.springcloud.clients.config.handleError;

import org.grandmasfood.springcloud.clients.model.dto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode("400");
        error.setTimestamp(LocalDateTime.now());
        error.setDescription("Data integrity violation");
        error.setException(ex.getMessage().split("/")[1].trim());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);


    }

}