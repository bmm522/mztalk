package com.mztalk.resource.service;

import com.mztalk.resource.domain.request.dto.FileRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InsertFileService {
    ResponseEntity<?> insertFile(MultipartFile multipartFile, FileRequestDto fileRequestDto);

    ResponseEntity<?> insertFiles(List<MultipartFile> multipartFileList, FileRequestDto fileRequestDto);
}
