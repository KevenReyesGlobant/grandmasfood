package org.grandmasfood.springcloud.products.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.products.domain.model.enums.Category;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    //    private Long idProduct;
    private UUID uuid;
    private String fantasyName;
    private Category category;
    private String description;
    private float price;
    private boolean available;
    private Boolean active;
}
