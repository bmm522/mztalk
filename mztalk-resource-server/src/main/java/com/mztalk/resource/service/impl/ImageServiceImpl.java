package com.mztalk.resource.service.impl;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {


    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public ImagesDto getImageInfo(long bNo) {
        return imageRepository.findByBNo(bNo);
    }
}
