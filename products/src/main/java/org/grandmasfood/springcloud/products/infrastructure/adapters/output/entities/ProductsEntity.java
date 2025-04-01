package org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;

import java.util.UUID;

@Entity(name="ProductsEntity")
@Table(name = "products_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idProduct;

    private UUID uuid;

    @Column(unique = true)
    @Size(max = 255, message = "Fantasy name must be at most 255 characters")
    @NotEmpty(message = "Fantasy name cannot be empty")
    private String fantasyName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Description cannot be null")
    @Size(max = 511, message = "Fantasy name must be at most 511 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private float price;

    private boolean available;

    private Boolean active;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}