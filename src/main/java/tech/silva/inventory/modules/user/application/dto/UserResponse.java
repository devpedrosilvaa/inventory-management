package tech.silva.inventory.modules.user.application.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role
) {
}
