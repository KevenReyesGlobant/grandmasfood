
package org.grandmasfood.springcloud.products.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductsDTO(

        @NotEmpty(message = "UUID cannot be empty")
        String uuid,
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
        boolean available
) {
}