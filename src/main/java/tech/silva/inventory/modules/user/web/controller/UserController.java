package tech.silva.inventory.modules.user.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.user.application.dto.UserCreateRequest;
import tech.silva.inventory.modules.user.application.dto.UserResponse;
import tech.silva.inventory.modules.user.application.dto.mapper.UserDtoMapper;
import tech.silva.inventory.modules.user.application.service.UserService;
import tech.silva.inventory.modules.user.application.service.SellerService;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid  UserCreateRequest user) {
        User domain = userService.createUser(UserMapper.toDomainFromCreateRequest(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoMapper.toUserResponseFromDomain(domain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(UserDtoMapper.toUserResponseFromDomain(userService.getUserById(id)));
    }
}

