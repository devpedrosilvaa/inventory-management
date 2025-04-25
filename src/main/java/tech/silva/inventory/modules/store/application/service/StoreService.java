package tech.silva.inventory.modules.store.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.auth.application.api.AuthApplicationService;
import tech.silva.inventory.modules.shared.exceptions.CnpjAlreadyExistException;
import tech.silva.inventory.modules.shared.exceptions.ObjectNotFoundException;
import tech.silva.inventory.modules.shared.exceptions.UserAlreadyHaveStoreException;
import tech.silva.inventory.modules.shared.exceptions.UserWithoutStoreException;
import tech.silva.inventory.modules.store.application.dto.StoreUpdateRequest;
import tech.silva.inventory.modules.store.domain.model.Address;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;
import tech.silva.inventory.modules.store.infrastructure.persistence.JpaStoreRepository;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;

@Service
public class StoreService {
    private final JpaStoreRepository storeRepository;
    private final UserApplicationService userService;
    private final AuthApplicationService authService;

    public StoreService(JpaStoreRepository storeRepository, UserApplicationService userService, AuthApplicationService authService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
        this.authService = authService;
    }

    @Transactional
    public Store saveStore(Store store){
        Long idUser = authService.getCurrentId();
        if (storeRepository.findByUserId(idUser).isPresent())
            throw new UserAlreadyHaveStoreException();
        if (storeRepository.findByCnpj(store.getCnpj()).isPresent())
            throw new CnpjAlreadyExistException("Cnpj already registered.");
        store.setIdUser(idUser);
        store = storeRepository.save(store);
        userService.addStore(store.getIdUser(), store.getId());
        return store;
    }

    @Transactional(readOnly = true)
    public Store getStoreByUser(){
        Long idUser = authService.getCurrentId();
        Long storeId = userService.getStoreIdByUserId(idUser);
        if (storeId == null)
            throw new UserWithoutStoreException("User without store in your register");

        return storeRepository.findById(storeId).orElseThrow(
                () -> new ObjectNotFoundException("Store not found, try again!")
        );
    }

    @Transactional
    public Store updateAddress(Long idStore, Address address){
        Store store = storeRepository.findById(idStore).orElseThrow(
                () -> new ObjectNotFoundException("Store not found, try again!")
        );

        store.setAddress(address);
        return storeRepository.save(store);
    }

    @Transactional
    public Store updateDataStore(Long idStore, StoreUpdateRequest store){
        Store storeSaved = storeRepository.findById(idStore).orElseThrow(
                () -> new ObjectNotFoundException("Store not found, try again!")
        );

        StoreMapper.toDomainFromUpdateRequest(store, storeSaved);
        System.out.println(storeSaved.getName());
        return storeRepository.save(storeSaved);
    }

    @Transactional
    public void deleteStore(Long id){
        storeRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Store not found, try again!")
        );
        storeRepository.deleteById(id);
    }
}
