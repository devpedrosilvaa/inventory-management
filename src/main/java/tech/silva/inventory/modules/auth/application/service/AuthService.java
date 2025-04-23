package tech.silva.inventory.modules.auth.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtToken;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetailsService;
import tech.silva.inventory.modules.shared.exceptions.InvalidCredencialException;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;
import tech.silva.inventory.modules.user.domain.model.User;

@Service
public class AuthService {

    private final UserApplicationService userService;
    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserApplicationService userService, JwtUserDetailsService detailsService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.detailsService = detailsService;
        this.authenticationManager = authenticationManager;
    }

    public JwtToken authenticate(String username, String password){
        try {
            if (!username.contains("@")) {
                User user = userService.getUserByEmail(username);
                username = user.getEmail();
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(authenticationToken);

            return detailsService.getTokenAuthenticated(username);

        } catch (AuthenticationException ex) {
            throw new InvalidCredencialException("Invalid credentials for user: " + username);
        }
    }
}
