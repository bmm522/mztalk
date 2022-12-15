package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Result;
import org.springframework.http.ResponseEntity;

public interface SelectImageService {
    ResponseEntity getImageInfo(long bNo, String serviceName);

    ResponseEntity getMainImage(long bNo, String serviceName);

    ResponseEntity getSubImages(long bNo, String serviceName);
}
