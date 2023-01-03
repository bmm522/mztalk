package com.mztalk.mentor.domain;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class ImportAccessToken {
    private String code;
    private String message;
    private HashMap<String,String> response;
}
