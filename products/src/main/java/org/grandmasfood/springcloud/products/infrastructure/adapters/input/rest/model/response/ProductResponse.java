package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response;

import java.util.UUID;

import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long idProduct;
    private UUID uuid;
    private String fantasyName;
    private Category category;
    private String description;
    private float price;
    private boolean available;
    private Boolean active;
}
