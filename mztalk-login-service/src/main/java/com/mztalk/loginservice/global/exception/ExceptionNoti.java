package com.mztalk.loginservice.global.exception;

import com.mztalk.loginservice.global.dto.ClientResponseDto;
import com.mztalk.loginservice.global.dto.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionNoti {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e){
        return new ResponseEntity<>(ClientResponseDto.builder().code(Code.FAIL.getCode()).msg(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
