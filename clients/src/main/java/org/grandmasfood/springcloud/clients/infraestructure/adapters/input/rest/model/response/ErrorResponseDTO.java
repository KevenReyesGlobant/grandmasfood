package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

