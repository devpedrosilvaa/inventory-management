package tech.silva.inventory.modules.store.infrastructure.mapper;

import tech.silva.inventory.modules.store.application.dto.StoreCreateRequest;
import tech.silva.inventory.modules.store.application.dto.StoreResponse;
import tech.silva.inventory.modules.store.application.dto.StoreUpdateRequest;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.persistence.entity.StoreEntity;

public class StoreMapper {
    public static Store toDomainFromEntity(StoreEntity store){
        return new Store(
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

    public static Store toDomainFromCreateRequest(StoreCreateRequest store) {
        return new Store(
                store.name(),
                store.description(),
                store.email(),
                store.phoneNumber(),
                store.cnpj(),
                store.address()
        );
    }

    public static void toDomainFromUpdateRequest(StoreUpdateRequest store, Store storeSaved) {
        storeSaved.setName(store.name());
        storeSaved.setDescription(store.description());
        storeSaved.setEmail(store.email());
        storeSaved.setPhoneNumber(store.phoneNumber());
        storeSaved.setCnpj(store.cnpj());
    }
}
