package com.mztalk.resource.factory;

import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotiResponseFactory {


    public static ResponseEntity badRequestWhenInsert(){
        return new ResponseEntity(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.UPLOAD_FAIL,0), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity serverErrorWhenInsert(){
        return new ResponseEntity(ResponseData.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity successWhenInsert(){
        return new ResponseEntity(ResponseData.res(StatusCode.OK, ResponseMessage.UPLOAD_SUCCESS,1), HttpStatus.OK);
    }
}
