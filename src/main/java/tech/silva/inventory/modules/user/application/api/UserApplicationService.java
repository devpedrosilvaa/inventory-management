package tech.silva.inventory.modules.user.application.api;

import tech.silva.inventory.modules.user.domain.model.User;

public interface UserApplicationService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
