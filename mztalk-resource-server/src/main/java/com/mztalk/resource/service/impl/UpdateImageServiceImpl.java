package com.mztalk.resource.service.impl;

import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.UpdateImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateImageServiceImpl implements UpdateImageService {



   private final ImageRepository imageRepository;

    @Override
    public int changeMainImage(long bNo, String serviceName, String imageName) {

        if(imageRepository.changeMainImageToSubImage(bNo, serviceName) != 1){
            log.error("Update Error changeMainImageToSubImage");
            return 0;
        }
        imageRepository.commit();

        if(imageRepository.changeSubImageToMainImage(imageName) != 1){
            log.error("Update Error changeSubImageToMainImage");
            return 0;
        }

        return 1;
    }
}
