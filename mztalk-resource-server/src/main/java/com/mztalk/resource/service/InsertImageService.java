package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InsertImageService {
    void insertImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException;
    String uploadImage(MultipartFile multipartFile, ImagesDto imagesDto)  throws IOException;


}
