package com.mztalk.resource.service;

import com.mztalk.resource.domain.request.dto.ImagesRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InsertImageService {
    ResponseEntity<?> insertImage(MultipartFile multipartFile, ImagesRequestDto imagesRequestDto) throws IOException;

    ResponseEntity<?> insertImages(List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto);

    ResponseEntity<?> insertMainImage(MultipartFile multipartFile, ImagesRequestDto imagesRequestDto);

    ResponseEntity<?> updateImage(List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto);
}
