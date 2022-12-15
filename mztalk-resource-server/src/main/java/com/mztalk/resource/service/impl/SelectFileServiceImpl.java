package com.mztalk.resource.service.impl;

import com.mztalk.resource.domain.dto.FileDto;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.File;
import com.mztalk.resource.domain.entity.Images;
import com.mztalk.resource.repository.FileRepository;
import com.mztalk.resource.service.SelectFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SelectFileServiceImpl implements SelectFileService {

    private final FileRepository fileRepository;
    @Override
    public ResponseEntity<?> getFiles(long id) {
        List<FileDto> fileDtoList = null;

        try {

            List<File> fileList = fileRepository.getFileInfo(id);
            fileDtoList = fileList.stream().map(FileDto::new).collect(Collectors.toList());

        } catch (NoResultException e){

            return badRequestWhenSelect();

        } catch (Exception e){

            return serverError();

        }

        return successWhenSelect(fileDtoList);
    }
}
