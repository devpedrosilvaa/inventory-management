package tech.silva.inventory.modules.store.domain.repository;

import org.springframework.data.jpa.repository.Query;
import tech.silva.inventory.modules.store.domain.model.Store;

import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    Optional<Store> findByUserId(Long idUser);
    Optional<Store> findByCnpj(String cnpj);
}
