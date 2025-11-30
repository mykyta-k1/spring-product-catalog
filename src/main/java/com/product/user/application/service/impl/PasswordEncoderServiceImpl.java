package com.product.user.application.service.impl;

import com.product.user.application.service.contract.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public String hashPassword(String password) {
    return bCryptPasswordEncoder.encode(password);
  }

  @Override
  public boolean checkPassword(String plainPassword, String hashedPassword) {
    return bCryptPasswordEncoder.matches(plainPassword, hashedPassword);
  }
}
