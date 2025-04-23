package tech.silva.inventory.modules.auth.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.silva.inventory.modules.shared.enums.Role;
import tech.silva.inventory.modules.user.application.api.UserApplicationService;
import tech.silva.inventory.modules.user.domain.model.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserApplicationService userService;

    public JwtUserDetailsService(UserApplicationService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username) throws UsernameNotFoundException {
        Role role = userService.getUserByEmail(username).getRole();
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
