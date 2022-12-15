package com.mztalk.resource.service.impl;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.domain.entity.Result;
import com.mztalk.resource.repository.ImageRepository;
import com.mztalk.resource.service.SelectImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SelectImageServiceImpl implements SelectImageService {


    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public Result getImageInfo(long bNo,String serviceName) {
        List<Images> imagesList = imageRepository.getImageInfo(bNo, serviceName);
        List<ImagesDto> imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        return new Result(imagesDtoList);
    }

    @Override
    public Result getSubImages(long bNo, String serviceName) {
        List<Images> imagesList = imageRepository.getSubImages(bNo, serviceName);
        List<ImagesDto> imagesDtoList = imagesList.stream().map(ImagesDto::new).collect(Collectors.toList());
        return new Result(imagesDtoList);
    }

    @Override
    public ImagesDto getMainImage(long bNo, String serviceName) {
        Images image = null;
        try{
            image = imageRepository.getMainImage(bNo, serviceName);
        } catch (NoResultException e){
            e.getMessage();
        }
        return new ImagesDto(image);
    }




}
