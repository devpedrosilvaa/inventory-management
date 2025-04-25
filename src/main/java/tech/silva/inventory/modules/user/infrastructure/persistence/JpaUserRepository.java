package tech.silva.inventory.modules.user.infrastructure.persistence;


import org.springframework.stereotype.Repository;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserMapper;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserPersistenceMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringUserJpaRepository jpaRepository;

    public JpaUserRepository(SpringUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomainFromEntity(jpaRepository.save(UserPersistenceMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserMapper::toDomainFromEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserMapper::toDomainFromEntity);
    }

    @Override
    public List<User> findAllByIdStore(Long idStore) {
        return jpaRepository.findAllByStoreId(idStore).stream()
                .map(UserMapper::toDomainFromEntity).collect(Collectors.toList());
    }
}