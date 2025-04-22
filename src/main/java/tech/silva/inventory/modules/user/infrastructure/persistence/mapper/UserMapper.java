package tech.silva.inventory.modules.user.infrastructure.persistence.mapper;

import tech.silva.inventory.modules.user.application.dto.UserCreateRequest;
import tech.silva.inventory.modules.user.application.dto.UserResponse;
import tech.silva.inventory.modules.user.domain.model.User;
import tech.silva.inventory.modules.user.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static User toDomainFromCreateRequest(UserCreateRequest user) {
        return new User(
                user.name(),
                user.email(),
                user.password(),
                user.role()
        );
    }

    public static User toDomainFromEntity(UserEntity user) {
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static UserResponse toUserResponseFromDomain(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }


}
