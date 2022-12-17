package com.mztalk.resource.factory;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class NotiResponseFactory {



    public static ResponseEntity<?> badRequestWhenInsert(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.UPLOAD_FAIL,0), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> serverError(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> successWhenInsert(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.OK, ResponseMessage.UPLOAD_SUCCESS,1), HttpStatus.OK);
    }

    public static ResponseEntity<?> badRequestWhenSelect(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_FILE, 0), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<?> successWhenSelect(T data){
        return new ResponseEntity<>(ResponseData.res(StatusCode.OK,ResponseMessage.READ_FILE_SUCCESS,data),HttpStatus.OK);
    }

    public static ResponseEntity<?> successWhenDelete(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.OK,ResponseMessage.DELETE_FILE_SUCCESS,1),HttpStatus.OK);
    }

    public static <T> ResponseEntity<?> successWhenSelect(List<T> dataList){
        return new ResponseEntity<>(ResponseData.res(StatusCode.OK,ResponseMessage.READ_FILE_SUCCESS,dataList),HttpStatus.OK);
    }

    public static ResponseEntity<?> successWhenUpdateMain(){
        return new ResponseEntity<>(ResponseData.res(StatusCode.OK, ResponseMessage.UPDATE_MAIN_SUCCESS,1), HttpStatus.OK);
    }

    public static ResponseEntity<?> badRequestWhenUpdateMain(){
       return new ResponseEntity<>(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.UPDATE_MAIN_FAIL, 0), HttpStatus.BAD_REQUEST);
    }

}
