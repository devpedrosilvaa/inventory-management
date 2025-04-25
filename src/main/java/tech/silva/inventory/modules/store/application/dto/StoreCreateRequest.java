package tech.silva.inventory.modules.store.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.silva.inventory.modules.store.domain.model.Address;

public record StoreCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank @Email(message = "email format is invalid", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String cnpj,
        @NotNull @Valid
        Address address
) {
}
