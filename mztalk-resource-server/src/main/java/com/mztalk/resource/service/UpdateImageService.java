package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateImageService {
//    ResponseEntity<?> changeMainImage(MultipartFile multipartFile, ImagesDto imagesDto);


    ResponseEntity<?> changeMainImage(long bNo, String serviceName, String imageName);
}
