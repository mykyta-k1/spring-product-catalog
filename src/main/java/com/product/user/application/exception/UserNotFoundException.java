package com.product.user.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class UserNotFoundException extends ApiException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException() {
    super("User not found");
  }
}
