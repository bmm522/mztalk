package com.mztalk.resource.controller;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Result;
import com.mztalk.resource.service.InsertImageService;
import com.mztalk.resource.service.SelectImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ImageApiController {

    private final InsertImageService insertImageService;
    private final SelectImageService selectImageService;
    @PostMapping("/image")
    public void uploadImage(@RequestParam("image")MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        insertImageService.uploadImage(multipartFile, imagesDto);
    }

    @GetMapping("/image")
    public Result getImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getImageInfo(bNo, serviceName);
    }

    // 메인사진 0 , 서브사진 1
    @GetMapping("/mainImage")
    public ImagesDto getMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getMainImage(bNo, serviceName);
    }

//    @PatchMapping("/image")
//    public int updateImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
//        return imageService.updateImage(bNo, serviceName);
//    }
}
