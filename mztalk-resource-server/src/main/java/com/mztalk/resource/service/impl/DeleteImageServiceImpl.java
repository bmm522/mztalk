package com.mztalk.resource.service.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.DeleteImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteImageServiceImpl implements DeleteImageService {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


   private final ImageRepository imageRepository;

    @Override
    public int deleteImage(long bNo, String serviceName) {
        List<String> objectKeyList = imageRepository.getObjectKeyList(bNo, serviceName);

        for(String objectKey : objectKeyList ){
            try{
                deleteImage(objectKey);
            }
            catch (AmazonServiceException e){
                log.error("Fail Image Delete");
                return 0;
            }

        }

        return 1;
    }

    @Override
    public int deleteImageDetail(String imageName) {
        String objectKey = imageRepository.getObjectKey(imageName);

        try{
            deleteImage(objectKey);
        } catch (AmazonServiceException e){
            log.error("Fail Image Delete");
            return 0;
        }
        return 1;
    }

    private void deleteImage(String objectKey){

        final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

        amazonS3.deleteObject(bucket, objectKey);
        imageRepository.deleteByObjectKey(objectKey);

    }
}
