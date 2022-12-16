package com.mztalk.resource.service;

import com.mztalk.resource.domain.dto.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InsertFileService {
    ResponseEntity<?> insertFile(MultipartFile multipartFile, FileDto fileDto);

    ResponseEntity<?> insertFiles(List<MultipartFile> multipartFileList, FileDto fileDto);
}
