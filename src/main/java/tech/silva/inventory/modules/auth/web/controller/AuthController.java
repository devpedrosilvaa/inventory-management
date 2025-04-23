package tech.silva.inventory.modules.auth.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.auth.application.dto.AuthLoginRequest;
import tech.silva.inventory.modules.auth.application.service.AuthService;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtToken;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetails;
import tech.silva.inventory.modules.user.application.dto.UserResponse;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.infrastructure.persistence.mapper.UserMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<JwtToken> authenticate(@RequestBody @Valid AuthLoginRequest dto){
        JwtToken token = authService.authenticate(dto.email(), dto.password());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> authenticate(@AuthenticationPrincipal JwtUserDetails userDetails){
        User user = authService.getUserAuthenticated(userDetails.getId());
        return ResponseEntity.ok(UserMapper.toUserResponseFromDomain(user));
    }
}
