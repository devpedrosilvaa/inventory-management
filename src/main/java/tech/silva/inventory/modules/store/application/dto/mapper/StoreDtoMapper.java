package tech.silva.inventory.modules.store.application.dto.mapper;

import tech.silva.inventory.modules.store.application.dto.StoreResponse;
import tech.silva.inventory.modules.store.domain.model.Store;

public class StoreDtoMapper {
    public static StoreResponse toResponseFromDomain(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getDescription(),
                store.getEmail(),
                store.getPhoneNumber(),
                store.getCnpj(),
                store.getAddress(),
                store.getStatus(),
                store.getIdUser()
        );
    }
}
