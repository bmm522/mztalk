package com.mztalk.resource.service.impl;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.domain.response.ResponseMessage;
import com.mztalk.resource.domain.response.StatusCode;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.UpdateImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateImageServiceImpl implements UpdateImageService {


    private final S3Factory s3Factory;

   private final ImageRepository imageRepository;

//    @Override
//    public ResponseEntity<?> changeMainImage(MultipartFile multipartFile, ImagesDto imagesDto) {
//        try {
//
//            imageRepository.changeMainImageToSubImage(Long.parseLong(imagesDto.getBNo()), imagesDto.getServiceName());
//            imageRepository.commit();
//
//            String imageName = multipartFile.getOriginalFilename();
//            Images images = imagesDto.toImagesWhenMain(imageName, s3Factory.uploadImageToAwsS3(multipartFile));
//            imageRepository.save(images);
//        } catch (IOException e){
//            return badRequestWhenUpdateMain();
//        } catch (Exception e){
//            return serverError();
//        }
//        return successWhenUpdateMain();
//    }








    @Override
    public ResponseEntity<?> changeMainImage(long bNo, String serviceName, String imageName) {

        try {
            if (imageRepository.changeMainImageToSubImage(bNo, serviceName) != 1) {
                log.error("Update Error changeMainImageToSubImage");
                return new ResponseEntity(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_MAIN_IMAGE, 0), HttpStatus.BAD_REQUEST);
            }
            imageRepository.commit();

            if (imageRepository.changeSubImageToMainImage(imageName) != 1) {
                log.error("Update Error changeSubImageToMainImage");
                return new ResponseEntity(ResponseData.res(StatusCode.BAD_REQUEST, ResponseMessage.UPDATE_MAIN_FAIL, 0), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            log.error("Server Error");
            return new ResponseEntity(ResponseData.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR,0), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ResponseData.res(StatusCode.OK, ResponseMessage.UPDATE_MAIN_SUCCESS,1), HttpStatus.OK);
    }
}
