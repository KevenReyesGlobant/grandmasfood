package model.dto;

public record ClientDTO(
        Long id,
        String uuid,
        String name,
        String email,
        String document,
        String phone,
        String deliveryAddress) {
}
