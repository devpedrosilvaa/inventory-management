package tech.silva.inventory.modules.user.domain.repository;

import tech.silva.inventory.modules.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAllByIdStore(Long idStore);
}
