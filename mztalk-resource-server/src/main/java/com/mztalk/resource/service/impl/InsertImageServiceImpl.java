package com.mztalk.resource.service.impl;

import com.mztalk.resource.domain.Role;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.request.dto.ImagesRequestDto;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.InsertImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InsertImageServiceImpl implements InsertImageService {


    private final ImageRepository imageRepository;


    private final S3Factory s3Factory;


    @Override
    public ResponseEntity<?> insertImage(MultipartFile multipartFile, ImagesRequestDto imagesRequestDto){

       try{

           saveImages(multipartFile,imagesRequestDto, Role.UPLOAD_SUB);

       }  catch (IOException e){

           log.error("Fail Image Save");
           return badRequestWhenInsert();

       } catch (Exception e){

           log.error("Server Error");
           return serverError();

       }

       return successWhenInsert();
    }

    @Override
    public ResponseEntity<?> insertImages(List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto) {

        for(int i = 0 ; i < multipartFileList.size() ; i++){

            if(i == 0) {

                try {

                    saveImages(multipartFileList.get(i), imagesRequestDto, Role.UPLOAD_MAIN);
                    imageRepository.commit();

                } catch (IOException e){

                    log.error("Fail Images Save");
                    return badRequestWhenInsert();

                } catch (Exception e){
                    System.out.println(e.getMessage());
                    log.error("Server Error");
                    return serverError();

                }
            } else {

                try {

                    saveImages(multipartFileList.get(i),imagesRequestDto,Role.UPLOAD_SUB);

                } catch (IOException e){

                    log.error("Fail Images Save");
                    return badRequestWhenInsert();

                } catch (Exception e){

                    log.error("Server Error");
                    return serverError();

                }
            }
        }

        return successWhenInsert();
    }

    @Override
    public ResponseEntity<?> updateImage(List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto) {
        List<Images> imagesList = imageRepository.findBybNo(imagesRequestDto.getBNo());
        int cnt = 0;
        System.out.println("asdfadsfasdfsadfasdf");
        System.out.println(multipartFileList.size());
        for(Images images : imagesList){
            if(images.getImageLevel() == 0){
                cnt++;
            }
        }

        if(cnt==0) {

            for(int i = 0 ; i < multipartFileList.size() ; i++){

                if(i == 0) {

                    try {

                        saveImages(multipartFileList.get(i), imagesRequestDto, Role.UPLOAD_MAIN);
                        imageRepository.commit();

                    } catch (IOException e){

                        log.error("Fail Images Save");
                        return badRequestWhenInsert();

                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        log.error("Server Error");
                        return serverError();

                    }
                } else {

                    try {

                        saveImages(multipartFileList.get(i),imagesRequestDto,Role.UPLOAD_SUB);

                    } catch (IOException e){

                        log.error("Fail Images Save");
                        return badRequestWhenInsert();

                    } catch (Exception e){

                        log.error("Server Error");
                        return serverError();

                    }
                }
            }
        } else {
            for(int i = 0 ; i < multipartFileList.size() ; i++) {

                try {

                    saveImages(multipartFileList.get(i),imagesRequestDto,Role.UPLOAD_SUB);

                } catch (IOException e){

                    log.error("Fail Images Save");
                    return badRequestWhenInsert();

                } catch (Exception e){

                    log.error("Server Error");
                    return serverError();

                }
            }
        }

        return successWhenInsert();
    }

    @Override
    public ResponseEntity<?> insertMainImage(MultipartFile multipartFile, ImagesRequestDto imagesRequestDto) {
        try{

            imageRepository.changeMainImageToSubImage(imagesRequestDto.getBNo(), imagesRequestDto.getServiceName());
            imageRepository.commit();
            saveImages(multipartFile,imagesRequestDto, Role.UPLOAD_MAIN);

        } catch (IOException e){

            log.error("Fail Image Save");
            return badRequestWhenInsert();

        } catch (Exception e){

            log.error("Server Error");
            return serverError();

        }

        return successWhenInsert();
    }



    private void saveImages(MultipartFile multipartFile, ImagesRequestDto imagesRequestDto, Role role) throws IOException {
        String imageName = multipartFile.getOriginalFilename();
        Images images =null;

            switch (role){
                case UPLOAD_MAIN:
                    images = imagesRequestDto.toImagesWhenMain(imageName, s3Factory.uploadImageToAwsS3(multipartFile));
                    break;
                case UPLOAD_SUB:
                    images = imagesRequestDto.toImagesWhenSub(imageName,s3Factory.uploadImageToAwsS3(multipartFile));
                    break;
            }
            imageRepository.save(images);
    }


}


