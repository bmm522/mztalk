package com.mztalk.login.exception;

public class ChangeFailException extends RuntimeException{
    public ChangeFailException() {
        super();
    }

    public ChangeFailException(String message) {
        super(message);
    }

    public ChangeFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeFailException(Throwable cause) {
        super(cause);
    }

    protected ChangeFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
