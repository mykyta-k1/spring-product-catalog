package com.product.product.application.exception;

import static org.springframework.http.HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS;

import com.product.shared.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(UNAVAILABLE_FOR_LEGAL_REASONS)
public class ProductUnavailableException extends ApiException {

    public ProductUnavailableException(String message) {
        super(message);
    }

    public ProductUnavailableException(String message, String messageLog) {
        super(message, messageLog);
    }

    public ProductUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductUnavailableException(String message, String messageLog, Throwable cause) {
        super(message, messageLog, cause);
    }
}
