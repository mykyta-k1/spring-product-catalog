package com.product.product.application.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException() {
        super("Brand Not Found");
    }
}
