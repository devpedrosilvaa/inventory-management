package tech.silva.inventory.modules.store.infrastructure.mapper;

import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.persistence.entity.StoreEntity;

public class StorePersistenceMapper {

    public static StoreEntity toEntityFromDomain(Store store){
        return new StoreEntity(
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
