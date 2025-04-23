package tech.silva.inventory.modules.store.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.shared.exceptions.CnpjAlreadyExistException;
import tech.silva.inventory.modules.shared.exceptions.UserAlreadyHaveStoreException;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.persistence.JpaStoreRepository;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;

@Service
public class StoreService {
    private final JpaStoreRepository storeRepository;
    private final UserApplicationService userService;

    public StoreService(JpaStoreRepository storeRepository, UserApplicationService userService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Transactional
    public Store saveStore(Store store, Long idUser){
        if (storeRepository.findByUserId(idUser).isPresent())
            throw new UserAlreadyHaveStoreException();
        if (storeRepository.findByCnpj(store.getCnpj()).isPresent())
            throw new CnpjAlreadyExistException("Cnpj already registered.");
        store.setIdUser(idUser);
        store = storeRepository.save(store);
        userService.addStore(store.getIdUser(), store.getId());
        return store;
    }
}
