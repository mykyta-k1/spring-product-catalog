package com.product.user.application.service.contract;

public interface PasswordEncoderService {

  String hashPassword(String password);

  boolean checkPassword(String plainPassword, String hashedPassword);
}
