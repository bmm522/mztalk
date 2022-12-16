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

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SelectImageServiceImpl implements SelectImageService {


    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<?> getImageInfo(long bNo,String serviceName) {
        List<ImagesDto> imagesDtoList = null;
        try {
            List<Images> imagesList = imageRepository.getImageInfo(bNo, serviceName);
            imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            return serverError();
        }
        return successWhenSelect(imagesDtoList);
    }

    @Override
    public ResponseEntity<?> getSubImages(long bNo, String serviceName) {
        List<ImagesDto> imagesDtoList = null;
        try{
            List<Images> imagesList = imageRepository.getSubImages(bNo, serviceName);
            imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            return serverError();
        }
        return successWhenSelect(imagesDtoList);
    }

    @Override
    public ResponseEntity<?> getMainImage(long bNo, String serviceName) {
        ImagesDto imagesDto = null;
        try{
            imagesDto = new ImagesDto(imageRepository.getMainImage(bNo, serviceName));
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            return serverError();
        }
        return successWhenSelect(imagesDto);
    }


}
