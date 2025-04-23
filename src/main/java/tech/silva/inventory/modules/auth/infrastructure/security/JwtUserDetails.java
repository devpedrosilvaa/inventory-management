package tech.silva.inventory.modules.auth.infrastructure.security;


import org.springframework.security.core.authority.AuthorityUtils;
import tech.silva.inventory.modules.auth.domain.model.AuthUser;
import tech.silva.inventory.modules.user.domain.model.User;

public class JwtUserDetails extends org.springframework.security.core.userdetails.User {
    private final AuthUser user;

    public JwtUserDetails(AuthUser user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public Long getId(){
        return this.user.getId();
    }

    public String getRole(){
        return this.user.getRole().name();
    }
}
