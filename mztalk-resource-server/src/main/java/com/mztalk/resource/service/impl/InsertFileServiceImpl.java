package com.mztalk.resource.service.impl;


import com.mztalk.resource.domain.entity.File;
import com.mztalk.resource.domain.request.dto.FileRequestDto;
import com.mztalk.resource.factory.S3Factory;
import com.mztalk.resource.repository.FileRepository;
import com.mztalk.resource.service.InsertFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mztalk.resource.factory.NotiResponseFactory.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InsertFileServiceImpl implements InsertFileService {

    private final FileRepository fileRepository;

    private final S3Factory s3Factory;
    @Override
    public ResponseEntity<?> insertFile(MultipartFile multipartFile, FileRequestDto fileRequestDto) {

        try{

            saveFile(multipartFile, fileRequestDto);

        } catch (IOException e) {

            log.error("Fail Image Save");
            return badRequestWhenInsert();

        }   catch (Exception e) {

            log.error("Server Error");
            return serverError();

        }

        return successWhenInsert();
    }

    @Override
    public ResponseEntity<?> insertFiles(List<MultipartFile> multipartFileList, FileRequestDto fileRequestDto) {

        for (MultipartFile multipartFile : multipartFileList) {

            try {

                saveFile(multipartFile, fileRequestDto);

            } catch (IOException e) {

                log.error("Fail Images Save");
                return badRequestWhenInsert();

            } catch (Exception e) {

                log.error("Server Error");
                return serverError();

            }

        }

        return successWhenInsert();
    }


    private void saveFile(MultipartFile multipartFile, FileRequestDto fileRequestDto) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
//        System.out.println("savefile : " + file.getFileName());
//        System.out.println("savefile : " + file.getFileId());
//        System.out.println("savefile : " + file.getFileUrl());
//        System.out.println("savefile : " + file.getObjectKey());

        File file = fileRequestDto.toEntity(fileName,s3Factory.uploadImageToAwsS3(multipartFile));

//        System.out.println("savefile : " + file.getFileName());

        fileRepository.save(file);
    }
}
