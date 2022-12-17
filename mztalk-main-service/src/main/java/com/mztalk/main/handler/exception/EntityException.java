package com.mztalk.main.handler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntityException extends RuntimeException {

    private final ExceptionCode exceptionCode;
}
