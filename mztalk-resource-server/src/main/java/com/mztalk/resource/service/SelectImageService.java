package com.mztalk.resource.service;


import org.springframework.http.ResponseEntity;

public interface SelectImageService {
    ResponseEntity<?> getImageInfo(long bNo, String serviceName);

    ResponseEntity<?> getMainImage(long bNo, String serviceName);

    ResponseEntity<?> getSubImages(long bNo, String serviceName);
}
