package model.dto;

import java.time.LocalDateTime;

public record OrdersDTO(

        String uuid,
        LocalDateTime creationDateTime,
        String clientDocument,
        String productUuid,
        int quantity,
        String extraInformation,
        float subTotal,
        float tax,
        float grandTotal,
        boolean delivered,
        LocalDateTime deliveredDate
) {
}
