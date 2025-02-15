package org.grandmasfood.springcloud.products.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull(message = "UUID cannot be empty")
    private UUID uuid;

    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasy_name;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private float price;

    private boolean available;

    @JsonIgnore
    private Boolean active;

    public Optional<Products> setInactiveProducts() {
        active = false;
        return Optional.empty();
    }
}