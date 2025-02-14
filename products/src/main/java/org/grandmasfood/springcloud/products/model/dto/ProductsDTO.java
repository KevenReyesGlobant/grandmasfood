
package org.grandmasfood.springcloud.products.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductsDTO(
        Long id,
        @NotEmpty(message = "UUID cannot be empty") String uuid,
        @NotEmpty(message = "Fantasy name cannot be empty") String fantasy_name,
        @NotEmpty(message = "Category cannot be empty") String category,
        String description,
        @NotNull(message = "Price cannot be null") @Positive(message = "Price must be positive") double price,
        boolean available
) {
}