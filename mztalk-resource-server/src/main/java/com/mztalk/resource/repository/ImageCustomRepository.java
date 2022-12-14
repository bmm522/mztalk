package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.Images;

import java.util.List;

public interface ImageCustomRepository {
    List<Images> getImageInfo(long bNo, String serviceName);
}
