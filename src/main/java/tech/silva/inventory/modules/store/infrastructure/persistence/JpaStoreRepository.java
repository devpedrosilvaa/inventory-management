package tech.silva.inventory.modules.store.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.domain.repository.StoreRepository;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;
import tech.silva.inventory.modules.store.infrastructure.mapper.StorePersistenceMapper;

import java.util.Optional;

@Repository
public class JpaStoreRepository implements StoreRepository {

    private final SpringJpaStoreRepository storeRepository;

    public JpaStoreRepository(SpringJpaStoreRepository springJpaStoreRepository) {
        this.storeRepository = springJpaStoreRepository;
    }

    @Override
    public Store save(Store store) {
        return StoreMapper.toDomainFromEntity(
            storeRepository.save(StorePersistenceMapper.toEntityFromDomain(store))
        );
    }

    public Optional<Store> findByUserId(Long idUser) {
        return storeRepository.findByUserId(idUser).map(StoreMapper::toDomainFromEntity);
    }

    public Optional<Store> findByCnpj(String cnpj) {
        return storeRepository.findByCnpj(cnpj).map(StoreMapper::toDomainFromEntity);
    }
}
