package com.mztalk.resource.controller;

import com.mztalk.resource.domain.dto.FileDto;
import com.mztalk.resource.service.InsertFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/resource")
@RestController
@RequiredArgsConstructor
public class FileApiController {


    private final InsertFileService insertFileService;
    // 파일 단일업로드
    @PostMapping("/file")
    public ResponseEntity<?> insertFile(@RequestParam("file")MultipartFile multipartFile, FileDto fileDto){
        return insertFileService.insertFile(multipartFile, fileDto);
    }

    @PostMapping("/files")
    public ResponseEntity<?> insertFiles(@RequestParam("files")List<MultipartFile> multipartFileList, FileDto fileDto){
        return insertFileService.insertFiles(multipartFileList, fileDto);
    }
}
