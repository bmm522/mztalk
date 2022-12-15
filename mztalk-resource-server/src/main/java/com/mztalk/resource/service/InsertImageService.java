package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InsertImageService {
    int insertImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException;

    int insertImages(List<MultipartFile> multipartFileList, ImagesDto imagesDto);

    int insertMainImage(MultipartFile multipartFile, ImagesDto imagesDto);
}
