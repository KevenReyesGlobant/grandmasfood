
package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;

public class ProductsCreateRequestDTO {
    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasyName;
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Price cannot be null or negative")
    @Positive(message = "Price must be positive")
    private float price;

    private Boolean available;

    private Boolean active;


    public ProductsCreateRequestDTO() {
        active = active == null ? true : active;
        available = available == null ? true : available;

    }


}