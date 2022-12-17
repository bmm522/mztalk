package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.FileDto;

public interface FileService {

    Long saveImage(FileDto fileDto);

    FileDto findById(Long id);

}
