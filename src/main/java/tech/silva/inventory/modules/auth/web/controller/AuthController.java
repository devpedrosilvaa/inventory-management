package tech.silva.inventory.modules.auth.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.silva.inventory.modules.auth.application.dto.AuthLoginRequest;
import tech.silva.inventory.modules.auth.application.service.AuthService;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtToken;

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
}
