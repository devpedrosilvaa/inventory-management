package tech.silva.inventory.modules.auth.infrastructure.security;


import org.springframework.security.core.authority.AuthorityUtils;
import tech.silva.inventory.modules.user.domain.model.User;

public class JwtUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public JwtUserDetails(User user) {
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
