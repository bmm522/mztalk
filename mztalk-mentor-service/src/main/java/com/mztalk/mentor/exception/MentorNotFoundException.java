package com.mztalk.mentor.exception;

public class MentorNotFoundException extends RuntimeException{
    public MentorNotFoundException() {
        super();
    }

    public MentorNotFoundException(String message) {
        super(message);
    }

    public MentorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MentorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MentorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
