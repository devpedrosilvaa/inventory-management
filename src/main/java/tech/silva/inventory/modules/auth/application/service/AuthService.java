package tech.silva.inventory.modules.auth.application.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.silva.inventory.modules.auth.application.api.AuthApplicationService;
import tech.silva.inventory.modules.auth.domain.model.AuthUser;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtToken;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetails;
import tech.silva.inventory.modules.auth.infrastructure.security.JwtUserDetailsService;
import tech.silva.inventory.modules.shared.enums.Role;
import tech.silva.inventory.modules.shared.exceptions.InvalidCredentialException;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;

@Service
public class AuthService  implements AuthApplicationService {

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
                AuthUser authUser = new AuthUser();
                BeanUtils.copyProperties(userService.getUserByEmailAuth(username), authUser);
                username = authUser.getEmail();
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(authenticationToken);

            return detailsService.getTokenAuthenticated(username);

        } catch (AuthenticationException ex) {
            throw new InvalidCredentialException("Invalid credentials for user: " + username);
        }
    }

    public AuthUser getUserAuthenticated(Long id){
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(userService.getUserByIdAuth(id), authUser);
        return authUser;
    }

    @Override
    public Long getCurrentId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        return userDetails.getId();
    }

    @Override
    public String getCurrentRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        return userDetails.getRole();
    }
}
