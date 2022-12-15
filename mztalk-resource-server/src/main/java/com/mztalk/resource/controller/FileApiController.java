package com.mztalk.resource.controller;

import com.mztalk.resource.domain.dto.FileDto;
import com.mztalk.resource.service.InsertFileService;
import com.mztalk.resource.service.SelectFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/resource")
@RestController
@RequiredArgsConstructor
public class FileApiController {


    private final InsertFileService insertFileService;

    private final SelectFileService selectFileService;
    // 파일 단일업로드
    @PostMapping("/file")
    public ResponseEntity<?> insertFile(@RequestParam("file")MultipartFile multipartFile, FileDto fileDto){
        return insertFileService.insertFile(multipartFile, fileDto);
    }

    // 파일 다중업로드
    @PostMapping("/files")
    public ResponseEntity<?> insertFiles(@RequestParam("files")List<MultipartFile> multipartFileList, FileDto fileDto){
        return insertFileService.insertFiles(multipartFileList, fileDto);
    }

    // 해당 유저의 모든 파일 불러오기
    @GetMapping(value = "/files" , consumes = "text/html")
    public ResponseEntity<?> getFiles(@RequestParam("id")long id){
        return selectFileService.getFiles(id);
    }
}
