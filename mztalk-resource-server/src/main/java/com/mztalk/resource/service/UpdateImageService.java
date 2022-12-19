package com.mztalk.resource.service;

import org.springframework.http.ResponseEntity;

public interface UpdateImageService {
//    ResponseEntity<?> changeMainImage(MultipartFile multipartFile, ImagesDto imagesDto);


    ResponseEntity<?> changeMainImage(long bNo, String serviceName, String objectKey);
}
