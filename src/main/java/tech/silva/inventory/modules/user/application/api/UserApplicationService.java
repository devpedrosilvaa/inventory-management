package tech.silva.inventory.modules.user.application.api;

import tech.silva.inventory.modules.user.application.dto.AuthUserView;
import tech.silva.inventory.modules.user.domain.model.User;

import java.util.List;

public interface UserApplicationService {
    AuthUserView getUserByIdAuth(Long id);
    AuthUserView getUserByEmailAuth(String email);
    void assignStoreToUser(Long idUser, Long idStore);
    Long getStoreIdByUserId(Long idUser);
    User addSellerUser(String name, String email, String password, Long idStore);
    List<User> listAllSellers(Long idStore);
}
