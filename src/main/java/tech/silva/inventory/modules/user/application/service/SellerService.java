package tech.silva.inventory.modules.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.auth.application.api.AuthApplicationService;
import tech.silva.inventory.modules.shared.enums.Role;
import tech.silva.inventory.modules.store.application.api.StoreApplicationService;
import tech.silva.inventory.modules.user.domain.model.User;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private UserService userService;
    private final StoreApplicationService storeService;
    private final AuthApplicationService authService;

    public SellerService(StoreApplicationService storeService, AuthApplicationService authService) {
        this.storeService = storeService;
        this.authService = authService;
    }

    @Transactional
    public List<User> sellerList(){
        Long idStore = userService.getStoreIdByUserId(authService.getCurrentId());
        return userService.listAllSellers(idStore);
    }

}
