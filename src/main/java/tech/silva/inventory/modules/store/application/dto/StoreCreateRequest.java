package tech.silva.inventory.modules.store.application.dto;

import tech.silva.inventory.modules.shared.enums.StatusStore;
import tech.silva.inventory.modules.store.domain.model.Address;

public record StoreCreateRequest(
        String name,
        String description,
        String email,
        String phoneNumber,
        String cnpj,
        Address address
) {
}
