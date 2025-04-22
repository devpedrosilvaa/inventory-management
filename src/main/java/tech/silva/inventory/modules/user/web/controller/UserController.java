package tech.silva.inventory.modules.user.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.user.application.dto.UserCreateRequest;
import tech.silva.inventory.modules.user.application.service.UserService;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserRepository userRepository) {
        this.userService = new UserService(userRepository);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid  UserCreateRequest user) {
        return ResponseEntity.ok(userService.createUser(UserMapper.toDomainFromCreateRequest(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}

