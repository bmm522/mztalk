package com.mztalk.resource.service;

import org.springframework.http.ResponseEntity;

public interface DeleteImageService {
    ResponseEntity deleteImage(long bNo, String serviceName);

    ResponseEntity deleteImageDetail(String imageName);
}
