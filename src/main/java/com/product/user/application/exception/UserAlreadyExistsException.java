package com.product.user.application.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(CONFLICT)
public class UserAlreadyExistsException extends ApiException {

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
