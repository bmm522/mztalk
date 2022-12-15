package com.mztalk.resource.repository;

import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;

import java.util.List;

public interface ImageCustomRepository {
    List<Images> getImageInfo(long bNo, String serviceName);

    Images getMainImage(long bNo, String serviceName);

    void commit();

    List getObjectKey(long bNo, String serviceName);
}
