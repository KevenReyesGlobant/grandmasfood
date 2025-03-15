package org.grandmasfood.springcloud.products.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ErrorResponseDTO {

    private String code;
    private String message;
    private String exception;
    private LocalDateTime timestamp;

}

