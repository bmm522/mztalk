package com.mztalk.mentor.exception;

public class MenteeNotFoundException extends RuntimeException{
    public MenteeNotFoundException() {
        super();
    }

    public MenteeNotFoundException(String message) {
        super(message);
    }

    public MenteeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenteeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MenteeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
