
package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class ProductsCreateRequestDTO {

    private UUID uuid;
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
        active = active == null || active;
        available = available == null || available;
    }


}