package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class BrandNotFoundException extends ApiException {

  public BrandNotFoundException(String message) {
    super(message);
  }

  public BrandNotFoundException() {
    super("Brand Not Found");
  }
}
