package com.product.user.application.mapper;

import com.product.user.application.dto.resp.UserUpdateResponse;
import com.product.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserUpdateResponse userToUserUpdateResponse(User user);
}
