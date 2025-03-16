package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

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
    private String exception;
    private List<String> message;
    private LocalDateTime timestamp;

}

