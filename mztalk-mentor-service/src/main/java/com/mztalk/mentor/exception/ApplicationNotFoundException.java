package com.mztalk.mentor.exception;

public class ApplicationNotFoundException extends IllegalStateException{
    public ApplicationNotFoundException() {
        super();
    }

    public ApplicationNotFoundException(String s) {
        super(s);
    }

    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationNotFoundException(Throwable cause) {
        super(cause);
    }
}
