package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.Images;

import java.util.List;

public interface ImageCustomRepository {
    List<Images> getImageInfo(long bNo, String serviceName);

    Images getMainImage(long bNo, String serviceName);

    void commit();

    List getObjectKeyList(long bNo, String serviceName);

    int changeMainImageToSubImage(long bNo, String serviceName);

    int changeSubImageToMainImage(String imageName);

    List<Images> getSubImages(long bNo, String serviceName);

    String getObjectKey(String imageName);

    int updateStatus(String objectKey);
}
