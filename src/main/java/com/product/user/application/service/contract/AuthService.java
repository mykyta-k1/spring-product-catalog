package com.product.user.application.service.contract;

import com.product.user.application.dto.req.UserLoginDto;
import com.product.user.application.dto.req.UserRegisterDto;

public interface AuthService {

    String login(UserLoginDto dto);

    void register(UserRegisterDto dto);
}
