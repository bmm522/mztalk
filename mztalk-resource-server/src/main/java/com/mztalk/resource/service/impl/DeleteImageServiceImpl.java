package com.mztalk.resource.service.impl;


import com.amazonaws.AmazonServiceException;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.DeleteImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mztalk.resource.factory.NotiResponseFactory.serverError;
import static com.mztalk.resource.factory.NotiResponseFactory.successWhenDelete;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteImageServiceImpl implements DeleteImageService {


    private final S3Factory s3Factory;

   private final ImageRepository imageRepository;

    @Override
    public ResponseEntity deleteImage(long bNo, String serviceName) {
        List<String> objectKeyList = imageRepository.getObjectKeyList(bNo, serviceName);

        for(String objectKey : objectKeyList ){
            try{
           //     s3Factory.deleteImage(objectKey);
                imageRepository.updateStatus(objectKey);
            }
            catch (AmazonServiceException e){
                log.error("Fail Image Delete");
                return serverError();
            }

        }

        return successWhenDelete();
    }

    @Override
    public ResponseEntity deleteImageDetail(String objectKey) {
        try{
            s3Factory.deleteImage(objectKey);
            imageRepository.updateStatus(objectKey);
        } catch (AmazonServiceException e){
            log.error("Fail Image Delete");
            return serverError();
        }
        return successWhenDelete();
    }




}
