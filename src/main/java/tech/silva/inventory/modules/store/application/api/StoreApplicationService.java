package tech.silva.inventory.modules.store.application.api;

import tech.silva.inventory.modules.store.domain.model.Store;

public interface StoreApplicationService {
    Store getById(Long id);
}
