package com.mztalk.resource.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mztalk.resource.domain.Role;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.InsertImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InsertImageServiceImpl implements InsertImageService {


    private final ImageRepository imageRepository;


    private final S3Factory s3Factory;


    @Override
    public ResponseEntity insertImage(MultipartFile multipartFile, ImagesDto imagesDto){

       try{

           saveImages(multipartFile,imagesDto, Role.UPLOAD_SUB);

       }  catch (IOException e){

           log.error("Fail Image Save");
           return badRequestWhenInsert();

       } catch (Exception e){

           log.error("Server Error");
           return serverErrorWhenInsert();

       }

       return successWhenInsert();
    }

    @Override
    public ResponseEntity insertImages(List<MultipartFile> multipartFileList, ImagesDto imagesDto) {

        for(int i = 0 ; i < multipartFileList.size() ; i++){

            if(i == 0) {

                try {

                    saveImages(multipartFileList.get(i), imagesDto, Role.UPLOAD_MAIN);

                } catch (IOException e){

                    log.error("Fail Images Save");
                    return badRequestWhenInsert();

                } catch (Exception e){

                    log.error("Server Error");
                    return serverErrorWhenInsert();

                }
            } else {

                try {

                    saveImages(multipartFileList.get(i),imagesDto,Role.UPLOAD_SUB);

                } catch (IOException e){

                    log.error("Fail Images Save");
                    return badRequestWhenInsert();

                } catch (Exception e){

                    log.error("Server Error");
                    return serverErrorWhenInsert();

                }
            }
        }

        return successWhenInsert();
    }


    @Override
    public ResponseEntity insertMainImage(MultipartFile multipartFile, ImagesDto imagesDto) {
        try{

            imageRepository.changeMainImageToSubImage(Long.parseLong(imagesDto.getBNo()), imagesDto.getServiceName());
            imageRepository.commit();
            saveImages(multipartFile,imagesDto, Role.UPLOAD_MAIN);

        } catch (IOException e){

            log.error("Fail Image Save");
            return badRequestWhenInsert();

        } catch (Exception e){

            log.error("Server Error");
            return serverErrorWhenInsert();

        }

        return successWhenInsert();
    }

    private void saveImages(MultipartFile multipartFile, ImagesDto imagesDto, Role role) throws IOException {
        String imageName = multipartFile.getOriginalFilename();
        Images images =null;

            switch (role){
                case UPLOAD_MAIN:
                    images = imagesDto.toImagesWhenMain(imageName, s3Factory.uploadImageToAwsS3(multipartFile));
                    break;
                case UPLOAD_SUB:
                    images = imagesDto.toImagesWhenSub(imageName,s3Factory.uploadImageToAwsS3(multipartFile));
                    break;
            }
            imageRepository.save(images);
    }


}


