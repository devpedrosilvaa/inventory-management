package tech.silva.inventory.modules.auth.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tech.silva.inventory.modules.auth.application.dto.AuthLoginRequest;
import tech.silva.inventory.modules.auth.application.dto.AuthUserResponse;
import tech.silva.inventory.modules.auth.application.service.AuthService;
import tech.silva.inventory.modules.auth.domain.model.AuthUser;
import tech.silva.inventory.modules.auth.infrastructure.mapper.AuthUserMapper;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtToken;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetails;

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
    public ResponseEntity<AuthUserResponse> authenticate(@AuthenticationPrincipal JwtUserDetails userDetails){
        AuthUser user = authService.getUserAuthenticated(userDetails.getId());
        return ResponseEntity.ok(AuthUserMapper.toAuthResponseFromAuthUser(user));
    }
}
