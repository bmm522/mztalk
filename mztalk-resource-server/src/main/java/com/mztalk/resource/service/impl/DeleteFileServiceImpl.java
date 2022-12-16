package com.mztalk.resource.service.impl;

import com.amazonaws.AmazonServiceException;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.FileRepository;
import com.mztalk.resource.service.DeleteFileService;
import com.mztalk.resource.service.DeleteImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mztalk.resource.factory.NotiResponseFactory.serverError;
import static com.mztalk.resource.factory.NotiResponseFactory.successWhenDelete;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DeleteFileServiceImpl implements DeleteFileService {

   private final FileRepository fileRepository;

   private final S3Factory s3Factory;

    @Override
    public ResponseEntity<?> deleteFile(long id) {
        List<String> objectKeyList = fileRepository.getObjectKeyList(id);

        System.out.println("요청여기까지옴");
        for(String objectKey : objectKeyList ){
            try{
                s3Factory.deleteImage(objectKey);
               fileRepository.deleteByObjectKey(objectKey);
            }
            catch (AmazonServiceException e){
                log.error("Fail Image Delete");
                return serverError();
            }

        }

        return successWhenDelete();
    }
}
