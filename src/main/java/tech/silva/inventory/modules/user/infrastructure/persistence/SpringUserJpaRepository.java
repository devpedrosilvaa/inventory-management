package tech.silva.inventory.modules.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.silva.inventory.modules.user.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

public interface SpringUserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}