package com.mztalk.resource.service.impl;

import com.mztalk.resource.domain.Role;
import com.mztalk.resource.domain.dto.FileDto;
import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.File;
import com.mztalk.resource.domain.entity.Images;
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
    public ResponseEntity<?> insertFile(MultipartFile multipartFile, FileDto fileDto) {

        try{

            saveFile(multipartFile, fileDto);

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
    public ResponseEntity<?> insertFiles(List<MultipartFile> multipartFileList, FileDto fileDto) {

        for (MultipartFile multipartFile : multipartFileList) {

            try {

                saveFile(multipartFile, fileDto);

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


    private void saveFile(MultipartFile multipartFile, FileDto fileDto) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        File file = fileDto.toEntity(fileName,s3Factory.uploadImageToAwsS3(multipartFile));

        fileRepository.save(file);
    }
}
