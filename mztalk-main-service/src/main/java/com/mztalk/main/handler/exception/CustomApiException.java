package com.mztalk.main.handler.exception;

public class CustomApiException extends EntityException {


   // private static final long serialVersionUID=1L;


    public CustomApiException(ExceptionCode exceptionCode) {

        super(exceptionCode);
    }
}
