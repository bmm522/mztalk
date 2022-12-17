package com.mztalk.main.handler.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_ACCOUNT(HttpStatus.BAD_REQUEST, "해당 계정은 존재하지 않습니다."),
    SAME_ACCOUNT(HttpStatus.BAD_REQUEST, "같은 계정입니다."),

    ALREADY_EXIST_FOLLOW(HttpStatus.BAD_REQUEST, "이미 Follow 관계입니다."),
    NOT_FOUND_FOLLOW(HttpStatus.BAD_REQUEST, "팔로워 관계가 아닙니다."),

    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "게시물이 존재하지 않습니다."),
    VERSION_CONFLICT(HttpStatus.CONFLICT, "다시 시도해주세요");


    private final HttpStatus httpStatus;
    private final String message;

}
