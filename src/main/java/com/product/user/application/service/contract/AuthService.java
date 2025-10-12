package com.product.user.application.service.contract;


import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;

public interface AuthService {

    String login(UserLoginDto dto);

    void register(UserRegisterDto dto);
}
