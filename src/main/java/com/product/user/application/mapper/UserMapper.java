package com.product.user.application.mapper;

import com.product.user.application.dto.req.UserDtoRequestFactory.UserUpdateRequest;
import com.product.user.application.dto.resp.UserUpdateResponse;
import com.product.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    void updateUserFromDto(UserUpdateRequest dto, @MappingTarget User user);

    UserUpdateResponse userToUserUpdateResponse(User user);
}
