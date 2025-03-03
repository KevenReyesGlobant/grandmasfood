package org.grandmasfood.springcloud.clients.model.dto.configDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDTO {
    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;
}

