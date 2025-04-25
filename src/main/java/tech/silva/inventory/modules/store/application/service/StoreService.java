package tech.silva.inventory.modules.store.application.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.silva.inventory.modules.auth.application.api.AuthApplicationService;
import tech.silva.inventory.modules.shared.exceptions.CnpjAlreadyExistException;
import tech.silva.inventory.modules.shared.exceptions.ObjectNotFoundException;
import tech.silva.inventory.modules.shared.exceptions.UserAlreadyHaveStoreException;
import tech.silva.inventory.modules.store.application.api.StoreApplicationService;
import tech.silva.inventory.modules.store.application.dto.SellerRequest;
import tech.silva.inventory.modules.store.application.dto.SellerResponse;
import tech.silva.inventory.modules.store.application.dto.StoreUpdateRequest;
import tech.silva.inventory.modules.store.domain.model.Address;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;
import tech.silva.inventory.modules.store.infrastructure.persistence.JpaStoreRepository;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements StoreApplicationService {
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
        userService.assignStoreToUser(store.getIdUser(), store.getId());
        return store;
    }

    @Transactional(readOnly = true)
    public Store getStoreByUser(){
        Long idUser = authService.getCurrentId();
        return storeRepository.findByUserId(idUser).orElseThrow(
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

    @Transactional(readOnly = true)
    @Override
    public Store getById(Long id) {
        return storeRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Store not found, try again!")
        );
    }

    @Transactional
    public SellerResponse registerSeller(SellerRequest sellerRequest, Long idStore){
        var sellerSaved = userService.addSellerUser(sellerRequest.getName(), sellerRequest.getEmail(),
                sellerRequest.getPassword(), idStore);
        return new SellerResponse(sellerSaved.getId(), sellerSaved.getName(), sellerSaved.getEmail());
    }

    @Transactional
    public List<SellerResponse> listAllSellers(){
        Long idUser = authService.getCurrentId();
        return userService.listAllSellers(idUser).stream()
                .map(user -> new SellerResponse(user.getId(), user.getName(), user.getEmail()))
                .toList();
    }
}
