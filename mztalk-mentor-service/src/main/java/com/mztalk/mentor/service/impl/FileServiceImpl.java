package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ImageDto;
import com.mztalk.mentor.repository.ImageRepository;
import com.mztalk.mentor.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Long save(ImageDto imageDto) {

        return null;
    }
}
