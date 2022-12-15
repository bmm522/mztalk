package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface InsertFileService {
    ResponseEntity<?> insertFile(MultipartFile multipartFile, FileDto fileDto);
}
