package com.mztalk.resource.controller;


import com.mztalk.resource.domain.dto.ImagesDto;
import com.mztalk.resource.domain.entity.Result;
import com.mztalk.resource.service.DeleteImageService;
import com.mztalk.resource.service.InsertImageService;
import com.mztalk.resource.service.SelectImageService;
import com.mztalk.resource.service.UpdateImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ImageApiController {

    private final InsertImageService insertImageService;

    private final SelectImageService selectImageService;

    private final UpdateImageService updateImageService;

    private final DeleteImageService deleteImageService;


    @PostMapping("/image")
    public int insertImage(@RequestParam("image")MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        return insertImageService.insertImage(multipartFile, imagesDto);
    }

    @PostMapping("/images")
    public int insertImages(@RequestParam("image") List<MultipartFile> multipartFileList, ImagesDto imagesDto){
        return insertImageService.insertImages(multipartFileList, imagesDto);
    }



    @GetMapping("/image")
    public Result getImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getImageInfo(bNo, serviceName);
    }

    // 메인사진 0 , 서브사진 1
    @GetMapping("/main-image")
    public ImagesDto getMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getMainImage(bNo, serviceName);
    }

    @PatchMapping(value="/main-image", consumes = "text/html")
    public int changeMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName, @RequestParam("imageName")String imageName){
        return updateImageService.changeMainImage(bNo, serviceName, imageName);
    }

//    @PatchMapping("/image")
//    public int updateImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
//        return imageService.updateImage(bNo, serviceName);
//    }

    @DeleteMapping("/image")
    public int deleteImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return deleteImageService.deleteImage(bNo, serviceName);
    }
}
