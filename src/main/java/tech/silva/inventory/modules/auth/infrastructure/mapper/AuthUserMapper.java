package tech.silva.inventory.modules.auth.infrastructure.mapper;

import tech.silva.inventory.modules.auth.application.dto.AuthUserResponse;
import tech.silva.inventory.modules.auth.domain.model.AuthUser;

public class AuthUserMapper {
    public static AuthUserResponse toAuthResponseFromAuthUser(AuthUser authUser){
        return new AuthUserResponse(
                authUser.getId(),
                authUser.getName(),
                authUser.getEmail(),
                authUser.getRole()
        );
    }
}
