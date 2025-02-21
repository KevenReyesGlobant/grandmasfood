package org.grandmasfood.springcloud.products.config.handleError;


import org.grandmasfood.springcloud.products.model.dto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


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
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponseDTO> cannotDeserializeException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.BAD_REQUEST.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription("Data integrity violation, verify values request...");
        String detailedMessage = extractDetailMessage(ex.getMessage());
        error.setException(detailedMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setCode(HttpStatus.BAD_GATEWAY.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setDescription(ex.getLocalizedMessage());
        error.setException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
    private String extractDetailMessage(String message) {
        String[] parts = message.split("Detail: ");
        if (parts.length > 1) {
            return parts[1].split("\\]")[0].trim();
        }
        return "No detailed message available";
    }

}