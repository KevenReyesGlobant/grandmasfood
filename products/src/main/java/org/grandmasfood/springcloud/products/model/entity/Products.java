package org.grandmasfood.springcloud.products.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.products.model.enums.Category;

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
    @JsonIgnore
    private UUID uuid;

    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasyName;

    @Enumerated(EnumType.STRING)
    private Category category;

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