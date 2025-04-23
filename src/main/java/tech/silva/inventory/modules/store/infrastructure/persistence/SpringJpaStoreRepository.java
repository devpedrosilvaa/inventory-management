package tech.silva.inventory.modules.store.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.silva.inventory.modules.store.infrastructure.persistence.entity.StoreEntity;

import java.util.Optional;

public interface SpringJpaStoreRepository extends JpaRepository<StoreEntity, Long> {
    @Query("SELECT s FROM StoreEntity s WHERE s.userId = :idUser")
    Optional<StoreEntity> findByUserId(@Param("idUser") Long idUser);
    Optional<StoreEntity> findByCnpj(String cnpj);
}
