package tech.silva.inventory.modules.user.application.dto;

import tech.silva.inventory.modules.shared.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role
) {
}
