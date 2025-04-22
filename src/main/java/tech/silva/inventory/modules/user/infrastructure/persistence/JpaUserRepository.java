package tech.silva.inventory.modules.user.infrastructure.persistence;


import org.springframework.stereotype.Repository;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;
import tech.silva.inventory.modules.user.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringUserJpaRepository jpaRepository;

    public JpaUserRepository(SpringUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return jpaRepository.save(UserEntity.toEntity(user)).toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserEntity::toDomain);
    }
}