package org.grandmasfood.springcloud.orders.config.handleError;

import jakarta.validation.ConstraintDefinitionException;
import org.grandmasfood.springcloud.clients.model.dto.configDto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.BAD_REQUEST.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription("Data integrity violation");
        String detailedMessage = extractDetailMessage(ex.getMessage());
        error.setException(detailedMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }
    @ExceptionHandler({ConstraintDefinitionException.class})
    public ResponseEntity<ErrorResponseDTO> handleDataViolationException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.BAD_REQUEST.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription("If delivered is true, delivery date cannot be empty");
        String detailedMessage = extractDetailMessage(ex.getMessage());
        error.setException(detailedMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.NOT_FOUND.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription(ex.getLocalizedMessage());
        error.setException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleServerException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription(ex.getLocalizedMessage());
        error.setException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }


    private String extractDetailMessage(String message) {
        String[] parts = message.split("Detail: ");
        if (parts.length > 1) {
            return parts[1].split("\\]")[0].trim();
        }
        return "No detailed message available";
    }

}