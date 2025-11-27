package com.product.user.application.service.contract;

import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserUpdateRequest;
import com.product.user.application.dto.resp.UserUpdateResponse;
import com.product.user.domain.model.User;
import java.util.UUID;

public interface UserService {

  void save(UserRegisterDto dto);

  UserUpdateResponse update(UserUpdateRequest dto, UUID currentUserId);

  void delete(UUID id);

  User findUserById(UUID id);

  boolean existsByEmail(String email);

  UUID checkUserAndPassword(UserLoginDto dto);

  void isExistsUserByEmail(String email);
}
