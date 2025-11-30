package com.product.user.application.service.impl;

import com.product.security.contract.JwtService;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;
import com.product.user.application.service.contract.AuthService;
import com.product.user.application.service.contract.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final JwtService jwtService;

  @Override
  public String login(UserLoginDto dto) {
    log.info("Checking user and password...");
    UUID userId = userService.checkUserAndPassword(dto);
    log.info("Generating jwt...");
    return jwtService.generateToken(userId);
  }

  @Override
  public void register(UserRegisterDto dto) {
    userService.isExistsUserByEmail(dto.getEmail());
    userService.save(dto);
  }
}
