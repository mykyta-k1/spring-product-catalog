package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class ProductNotFoundDataException extends ApiException {

    public ProductNotFoundDataException(String message) {
        super(message);
    }
}
