package com.mztalk.loginservice.global.dto;

public enum Code {

    SUCCESS(1) , FAIL(-1);

    private final Integer code;

    Code(Integer code) {this.code = code;}

    public Integer getCode(){return code;}
}
