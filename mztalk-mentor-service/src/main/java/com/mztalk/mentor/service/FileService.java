package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.FileDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface FileService {

    Long saveFile(FileDto fileDto);

    FileDto findById(Long id);

    void saveFiles(Long applicationId, HttpServletRequest request);

    ResponseEntity<String> getFile(Long applicationId, String authorization, String refreshToken);
}
