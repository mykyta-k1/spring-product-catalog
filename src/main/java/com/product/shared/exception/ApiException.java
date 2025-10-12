package com.product.shared.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private String messageLog = "Error Api Exception: ";

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message, String messageLog) {
        super(message);
        this.messageLog = messageLog;
    }

    public ApiException(String message, String messageLog, Throwable cause) {
        super(message, cause);
        this.messageLog = messageLog;
    }
}
