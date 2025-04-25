package tech.silva.inventory.modules.user.application.dto.mapper;

import tech.silva.inventory.modules.user.application.dto.AuthUserView;
import tech.silva.inventory.modules.user.application.dto.UserResponse;
import tech.silva.inventory.modules.user.domain.model.User;

public class UserDtoMapper {

    public static UserResponse toUserResponseFromDomain(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public static AuthUserView toAuthViewFromDomain(User user){
        return new AuthUserView(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
