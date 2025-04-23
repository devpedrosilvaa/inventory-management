package tech.silva.inventory.modules.store.domain.repository;

import tech.silva.inventory.modules.store.domain.model.Store;

public interface StoreRepository {
    Store save(Store store);
}
