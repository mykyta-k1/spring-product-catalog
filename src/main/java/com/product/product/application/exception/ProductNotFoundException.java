package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ProductNotFoundException extends ApiException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, String messageLog) {
        super(message, messageLog);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, null, cause);
    }

    public ProductNotFoundException() {
        super("Product not found");
    }
}
