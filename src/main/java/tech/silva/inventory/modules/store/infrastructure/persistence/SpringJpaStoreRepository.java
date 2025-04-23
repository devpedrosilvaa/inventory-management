package tech.silva.inventory.modules.store.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.inventory.modules.store.infrastructure.entity.StoreEntity;

public interface SpringJpaStoreRepository extends JpaRepository<StoreEntity, Long> {
}
