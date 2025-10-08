package com.product.product.application.exception;

public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public ProductTypeNotFoundException() {
        super("Product type not found");
    }
}
