package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public ProductTypeNotFoundException() {
        super("Product type not found");
    }
}
