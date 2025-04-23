package tech.silva.inventory.modules.store.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetails;
import tech.silva.inventory.modules.store.application.dto.StoreCreateRequest;
import tech.silva.inventory.modules.store.application.dto.StoreResponse;
import tech.silva.inventory.modules.store.application.service.StoreService;
import tech.silva.inventory.modules.store.domain.model.Store;
import tech.silva.inventory.modules.store.infrastructure.mapper.StoreMapper;

@RestController
@RequestMapping("api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StoreResponse> saveStore(@AuthenticationPrincipal JwtUserDetails userDetails,
                                                   @RequestBody @Valid StoreCreateRequest storeRequest){
        Store store = storeService.saveStore(
                StoreMapper.toDomainFromCreateRequest(storeRequest), userDetails.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(StoreMapper.toResponseFromDomain(store));
    }
}
