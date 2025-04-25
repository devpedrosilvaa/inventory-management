package tech.silva.inventory.modules.store.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.store.application.dto.StoreCreateRequest;
import tech.silva.inventory.modules.store.application.dto.StoreResponse;
import tech.silva.inventory.modules.store.application.dto.StoreUpdateRequest;
import tech.silva.inventory.modules.store.application.service.StoreService;
import tech.silva.inventory.modules.store.domain.model.Address;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;

@RestController
@RequestMapping("api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StoreResponse> saveStore(@RequestBody @Valid StoreCreateRequest storeRequest){
        Store store = storeService.saveStore(
                StoreMapper.toDomainFromCreateRequest(storeRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(StoreMapper.toResponseFromDomain(store));
    }

    @GetMapping("/me")
    public ResponseEntity<StoreResponse> getStoreByUserAuthenticated(){
        return ResponseEntity.ok(StoreMapper.toResponseFromDomain(
                    storeService.getStoreByUser()));
    }

    @PutMapping("/address/{idStore}")
    public ResponseEntity<StoreResponse> updateAddress(@RequestBody @Valid Address address,
                                                            @PathVariable Long idStore){
        Store store = storeService.updateAddress(idStore, address);
        return ResponseEntity.ok(StoreMapper.toResponseFromDomain(store));
    }

    @PutMapping("/{idStore}")
    public ResponseEntity<StoreResponse> updateDataStore(@RequestBody @Valid StoreUpdateRequest storeUpdateRequest,
                                                            @PathVariable Long idStore){
            Store store = storeService.updateDataStore(idStore, storeUpdateRequest);
            return ResponseEntity.ok(StoreMapper.toResponseFromDomain(store));
    }

    @DeleteMapping("/{idStore}")
    public ResponseEntity<StoreResponse> deleteStore(@PathVariable Long idStore){
            storeService.deleteStore(idStore);
            return ResponseEntity.ok().build();
    }
}
