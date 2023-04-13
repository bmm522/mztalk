package com.mztalk.loginservice.user.exception;

public class UserNoNotFoundException extends RuntimeException{

    public UserNoNotFoundException() {
        super();
    }

    public UserNoNotFoundException(String message) {
        super(message);
    }

    public UserNoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
