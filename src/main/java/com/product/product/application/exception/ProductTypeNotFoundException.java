package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ProductTypeNotFoundException extends ApiException {

  public ProductTypeNotFoundException(String message) {
    super(message);
  }

  public ProductTypeNotFoundException() {
    super("Product type not found");
  }
}
