package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.FileDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.File;
import com.mztalk.mentor.exception.ImageNotFoundException;
import com.mztalk.mentor.repository.FileRepository;
import com.mztalk.mentor.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public Long saveImage(FileDto fileDto) {
        Application application = fileDto.getApplication();
        File file = fileDto.toEntity();
        file.addApplication(application);
        return fileRepository.save(file).getId();
    }

    @Override
    public FileDto findById(Long id) {
        File file = fileRepository.findById(id).orElseThrow(()->new ImageNotFoundException("해당 서류가 존재하지 않습니다."));
        FileDto fileDto = new FileDto(file);
        return fileDto;
    }



}
