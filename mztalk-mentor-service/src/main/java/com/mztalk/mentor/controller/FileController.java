package com.mztalk.mentor.controller;

import com.mztalk.mentor.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/{id}")
    public ResponseEntity<String> getFile(@PathVariable("id")Long id){
//        fileService.getFile();
        return null;
    }
}
