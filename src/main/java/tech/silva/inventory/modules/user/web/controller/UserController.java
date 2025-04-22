package tech.silva.inventory.modules.user.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.user.application.service.UserService;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.domain.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserRepository repository) {
        this.userService = new UserService(repository);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}

