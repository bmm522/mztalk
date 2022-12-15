package com.mztalk.resource.service.impl;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.entity.Result;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.SelectImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mztalk.resource.domain.response.ResponseData;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SelectImageServiceImpl implements SelectImageService {


    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity getImageInfo(long bNo,String serviceName) {
        List<ImagesDto> imagesDtoList = null;
        try {
            List<Images> imagesList = imageRepository.getImageInfo(bNo, serviceName);
            imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequest();
        } catch (Exception e){
            return serverError();
        }
        return success(imagesDtoList);
    }

    @Override
    public ResponseEntity getSubImages(long bNo, String serviceName) {
        List<ImagesDto> imagesDtoList = null;
        try{
            List<Images> imagesList = imageRepository.getSubImages(bNo, serviceName);
            imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequest();
        } catch (Exception e){
            return serverError();
        }
        return success(imagesDtoList);
    }

    @Override
    public ResponseEntity getMainImage(long bNo, String serviceName) {
        ImagesDto imagesDto = null;
        try{
            imagesDto = new ImagesDto(imageRepository.getMainImage(bNo, serviceName));
        } catch (NoResultException e){
            return badRequest();
        } catch (Exception e){
            return serverError();
        }
        return success(imagesDto);
    }



    private ResponseEntity badRequest(){
        return new ResponseEntity(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_IMAGE), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity serverError(){
        return new ResponseEntity(ResponseData.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity success(ImagesDto imagesDto){
        return new ResponseEntity(ResponseData.res(StatusCode.OK,ResponseMessage.READ_IMAGE_SUCCESS,imagesDto),HttpStatus.OK);
    }

    private ResponseEntity success(List<ImagesDto> imagesDtoList){
        return new ResponseEntity(ResponseData.res(StatusCode.OK,ResponseMessage.READ_IMAGE_SUCCESS,imagesDtoList),HttpStatus.OK);
    }


}
