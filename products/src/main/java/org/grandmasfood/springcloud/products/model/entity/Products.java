package org.grandmasfood.springcloud.products.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UUID cannot be empty")
    private String uuid;

    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasy_name;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private float price;

    private boolean available;

    private Boolean active;

    public Optional<Products> setInactiveProducts() {
        active = false;
        return Optional.empty();
    }
}