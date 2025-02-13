package model.dto;

public record ProductsDTO(
        String uuid,
        String fantasy_name,
        String category,
        String description,
        float price,
        boolean available
) {
}
