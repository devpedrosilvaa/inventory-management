package tech.silva.inventory.modules.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import tech.silva.inventory.modules.shared.enums.Role;

public record UserCreateRequest (
        @NotBlank
        String name,
        @NotBlank @Email(message = "email format is invalid", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String email,
        @NotBlank
        String password
) {
}
