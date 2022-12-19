package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.FileDto;

import javax.servlet.http.HttpServletRequest;

public interface FileService {

    Long saveFile(FileDto fileDto);

    FileDto findById(Long id);

    void saveFiles(Long applicationId, HttpServletRequest request);
}
