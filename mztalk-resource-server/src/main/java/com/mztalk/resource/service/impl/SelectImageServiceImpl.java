package com.mztalk.resource.service.impl;



import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.response.dto.ImagesResponseDto;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.SelectImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
        List<ImagesResponseDto> imagesResponseDtoList = null;
        try {
//            Thread.sleep(100000000);
            List<Images> imagesList = imageRepository.getImageInfo(bNo, serviceName);
            imagesResponseDtoList = imagesList.stream().map(ImagesResponseDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            return serverError();
        }
        return successWhenSelect(imagesResponseDtoList);
    }

    @Override
    public ResponseEntity<?> getSubImages(long bNo, String serviceName) {
        List<ImagesResponseDto> imagesResponseDtoList = null;
        try{
            List<Images> imagesList = imageRepository.getSubImages(bNo, serviceName);
            imagesResponseDtoList = imagesList.stream().map(ImagesResponseDto::new).collect(Collectors.toList());
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            return serverError();
        }
        return successWhenSelect(imagesResponseDtoList);
    }

    @Override
    public ResponseEntity<?> getMainImage(long bNo, String serviceName) {

        System.out.println("main : " + bNo);
        System.out.println("main : " + serviceName);
        ImagesResponseDto imagesResponseDto = null;
        try{

            imagesResponseDto = new ImagesResponseDto(imageRepository.getMainImage(bNo, serviceName));
        } catch (NoResultException e){
            return badRequestWhenSelect();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return serverError();
        }
        return successWhenSelect(imagesResponseDto);
    }


}
