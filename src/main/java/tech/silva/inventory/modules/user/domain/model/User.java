package tech.silva.inventory.modules.user.domain.model;

import tech.silva.inventory.modules.shared.enums.Role;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role = Role.ROLE_ADMIN;
    private Long idStore;

    public User(){}

    public User(Long id, String name, String email, String password, Role role, Long idStore) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.idStore = idStore;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }

    public Long getIdStore() {
        return idStore;
    }
}
