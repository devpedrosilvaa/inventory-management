package tech.silva.inventory.modules.store.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.store.application.dto.*;
import tech.silva.inventory.modules.store.application.dto.mapper.StoreDtoMapper;
import tech.silva.inventory.modules.store.application.service.StoreService;
import tech.silva.inventory.modules.store.domain.model.Address;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;
import java.util.List;

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

        return ResponseEntity.status(HttpStatus.CREATED).body(StoreDtoMapper.toResponseFromDomain(store));
    }

    @GetMapping("/me")
    public ResponseEntity<StoreResponse> getStoreByUserAuthenticated(){
        return ResponseEntity.ok(StoreDtoMapper.toResponseFromDomain(
                    storeService.getStoreByUser()));
    }

    @PutMapping("/address/{idStore}")
    public ResponseEntity<StoreResponse> updateAddress(@RequestBody @Valid Address address,
                                                            @PathVariable Long idStore){
        Store store = storeService.updateAddress(idStore, address);
        return ResponseEntity.ok(StoreDtoMapper.toResponseFromDomain(store));
    }

    @PutMapping("/{idStore}")
    public ResponseEntity<StoreResponse> updateDataStore(@RequestBody @Valid StoreUpdateRequest storeUpdateRequest,
                                                            @PathVariable Long idStore){
            Store store = storeService.updateDataStore(idStore, storeUpdateRequest);
            return ResponseEntity.ok(StoreDtoMapper.toResponseFromDomain(store));
    }

    @DeleteMapping("/{idStore}")
    public ResponseEntity<StoreResponse> deleteStore(@PathVariable Long idStore){
            storeService.deleteStore(idStore);
            return ResponseEntity.ok().build();
    }

    @PostMapping("/sellers/{idStore}")
    public ResponseEntity<SellerResponse> saveSeller(@RequestBody @Valid SellerRequest dto,
                                                     @PathVariable Long idStore) {
        SellerResponse seller = storeService.registerSeller(dto, idStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(seller);
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerResponse>> getAllSeller() {
        List<SellerResponse> domain = storeService.listAllSellers();
        return ResponseEntity.status(HttpStatus.CREATED).body(domain);
    }
}
