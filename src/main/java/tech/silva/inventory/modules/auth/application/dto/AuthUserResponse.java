package tech.silva.inventory.modules.auth.application.dto;

import tech.silva.inventory.modules.shared.enums.Role;

public record AuthUserResponse(
        Long id,
        String name,
        String email,
        Role role
) {
}
