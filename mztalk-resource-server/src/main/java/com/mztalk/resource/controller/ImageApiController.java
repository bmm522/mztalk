package com.mztalk.resource.controller;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.service.ImageService;
import com.mztalk.resource.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ImageApiController {

    private final S3Service s3Service;
    private final ImageService imageService;
    @PostMapping("/image")
    public void uploadImage(@RequestParam("image")MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        s3Service.upload(multipartFile, imagesDto);
    }

    @GetMapping("/image/{bNo}")
    public ImagesDto getImage(@PathVariable("bNo")long bNo){
        return imageService.getImageInfo(bNo);
    }
}
