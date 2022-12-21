package com.mztalk.bung.exception;

public class AddBoardException extends RuntimeException{

    public AddBoardException() {
        super();
    }

    public AddBoardException(String message) {
        super(message);
    }

    public AddBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddBoardException(Throwable cause) {
        super(cause);
    }

    protected AddBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
