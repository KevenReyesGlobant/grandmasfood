package model.dto;

public record ClientDTO(
        String uuid,
        String name,
        String email,
        String document,
        String phone,
        String deliveryAddress) {
}
