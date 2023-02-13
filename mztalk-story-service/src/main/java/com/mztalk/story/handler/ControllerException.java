package com.mztalk.story.handler;


import com.mztalk.story.common.CMRespDto;
import com.mztalk.story.handler.exception.CustomApiException;
import com.mztalk.story.handler.exception.CustomException;
import com.mztalk.story.handler.exception.CustomValidationApiException;
import com.mztalk.story.handler.exception.CustomValidationException;

import com.mztalk.story.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerException {

    //RuntimeException이 발동하는 모든 exception을 가로챔
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {

        if(e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        }else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }

    //위에는 js가 응답이 되는거고 이건 object가 응답이 됨 다른거임
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);

    }




}



