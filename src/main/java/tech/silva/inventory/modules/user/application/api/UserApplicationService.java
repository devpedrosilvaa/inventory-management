package tech.silva.inventory.modules.user.application.api;

import tech.silva.inventory.modules.user.application.dto.AuthUserView;

public interface UserApplicationService {
    AuthUserView getUserByIdAuth(Long id);
    AuthUserView getUserByEmailAuth(String email);
    void addStore(Long idUser, Long idStore);
}
