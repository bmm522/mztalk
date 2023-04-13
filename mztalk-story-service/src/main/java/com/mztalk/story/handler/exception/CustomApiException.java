package com.mztalk.story.handler.exception;

public class CustomApiException extends RuntimeException {


    private static final long serialVersionUID=1L;


    public CustomApiException(String message) {
        super(message); //부모한테만 던짐
    }
}
