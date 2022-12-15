package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.ImagesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InsertImageService {
    ResponseEntity insertImage(MultipartFile multipartFile, ImagesDto imagesDto) throws IOException;

    ResponseEntity insertImages(List<MultipartFile> multipartFileList, ImagesDto imagesDto);

    ResponseEntity insertMainImage(MultipartFile multipartFile, ImagesDto imagesDto);
}
