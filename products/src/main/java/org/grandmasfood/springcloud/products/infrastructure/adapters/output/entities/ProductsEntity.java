package org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;

import java.util.UUID;

@Entity
@Table(name = "products_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private UUID uuid;

    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasyName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private float price;

    private boolean available;

    private Boolean active;

}