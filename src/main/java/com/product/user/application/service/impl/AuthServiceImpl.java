package com.product.user.application.service.impl;

import com.product.security.contract.JwtService;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;
import com.product.user.application.service.contract.AuthService;
import com.product.user.application.service.contract.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public String login(UserLoginDto dto) {
        UUID userId = userService.checkUserAndPassword(dto);
        return jwtService.generateToken(userId);
    }

    @Override
    public void register(UserRegisterDto dto) {
        userService.isExistsUserByEmail(dto.getEmail());
        userService.save(dto);
    }
}
