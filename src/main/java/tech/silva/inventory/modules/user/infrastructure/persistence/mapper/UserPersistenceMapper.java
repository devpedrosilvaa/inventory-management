package tech.silva.inventory.modules.user.infrastructure.persistence.mapper;

import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.infrastructure.persistence.entity.UserEntity;

public class UserPersistenceMapper {
    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getIdStore()
        );
    }


}
