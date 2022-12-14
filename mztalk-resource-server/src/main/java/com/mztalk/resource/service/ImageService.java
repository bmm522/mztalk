package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Result;

public interface ImageService {
    Result getImageInfo(long bNo, String serviceName);
}
