package com.mztalk.resource.service.impl;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.entity.Result;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {


    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public Result getImageInfo(long bNo,String serviceName) {
        List<Images> imagesList = imageRepository.getImageInfo(bNo, serviceName);
        List<ImagesDto> imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        return new Result(imagesDtoList);
    }
}
