
package org.grandmasfood.springcloud.products.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductsDTO(


        @NotEmpty(message = "Fantasy name cannot be empty")
        String fantasy_name,
        @NotEmpty(message = "Category cannot be empty")
        String category,
        @NotEmpty(message = "Description cannot be empty")
        String description,
        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be positive")
        float price,
        @NotNull(message = "Available cannot be null")
        Boolean available,

        Boolean active
) {


    public ProductsDTO {
        active = active == null ? true : active;
        available = available == null ? true : available;

    }


}