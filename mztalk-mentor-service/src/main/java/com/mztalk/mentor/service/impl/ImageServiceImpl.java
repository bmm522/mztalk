package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ImageDto;
import com.mztalk.mentor.domain.entity.Image;
import com.mztalk.mentor.exception.ImageNotFoundException;
import com.mztalk.mentor.repository.ImageRepository;
import com.mztalk.mentor.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Long saveImage(ImageDto imageDto) {
        return imageRepository.save(imageDto.toEntity()).getId();
    }

    @Override
    public ImageDto findById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(()->new ImageNotFoundException("해당 서류가 존재하지 않습니다."));
        ImageDto imageDto = new ImageDto(image);
        return imageDto;
    }



}
