package tech.silva.inventory.modules.user.infrastructure.persistence.entity;

import jakarta.persistence.*;
import tech.silva.inventory.modules.user.domain.model.User;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User toDomain() {
        return new User(id, name, email, password, role);
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.name = user.getName();
        entity.email = user.getEmail();
        entity.password = user.getPassword();
        entity.role = user.getRole();
        return entity;
    }
}
