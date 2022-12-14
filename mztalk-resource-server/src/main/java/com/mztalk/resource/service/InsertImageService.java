package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InsertImageService {

    void uploadImage(MultipartFile multipartFile, ImagesDto imagesDto)  throws IOException;
}
