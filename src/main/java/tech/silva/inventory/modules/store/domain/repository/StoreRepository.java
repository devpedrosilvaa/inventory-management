package tech.silva.inventory.modules.store.domain.repository;

import tech.silva.inventory.modules.store.domain.model.Store;

import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    Optional<Store> findByUserId(Long idUser);
    Optional<Store> findByCnpj(String cnpj);
    Optional<Store> findById(Long id);
    void deleteById(Long id);
}
