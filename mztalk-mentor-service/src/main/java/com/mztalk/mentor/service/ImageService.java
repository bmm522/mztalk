package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ImageDto;

public interface ImageService {

    Long saveImage(ImageDto imageDto);

    ImageDto findById(Long id);

}
