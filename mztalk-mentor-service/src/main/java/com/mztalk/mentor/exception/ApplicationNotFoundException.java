package com.mztalk.mentor.exception;

public class ApplicationNotFoundException extends RuntimeException{
    public ApplicationNotFoundException() {
        super();
    }
    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
