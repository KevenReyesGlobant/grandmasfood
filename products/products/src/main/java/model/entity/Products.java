package model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    @NotEmpty
    private String fantasy_name;
    @NotEmpty
    private String category;
    @NotEmpty
    private String description;
    @NotEmpty
    private float price;
    @NotEmpty
    private boolean available;

}
