package org.grandmasfood.springcloud.clients.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ErrorResponseDTO {

    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
//    private String code;
//    private LocalDateTime timestamp;
//    private String description;
//    private String exception;
}

