package tech.silva.inventory.modules.user.application.dto;

import tech.silva.inventory.modules.shared.enums.Role;

public class AuthUserView{
        private Long id;
        private String name;
        private String email;
        private String password;
        private Role role;

    public AuthUserView() {
    }

    public AuthUserView(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
