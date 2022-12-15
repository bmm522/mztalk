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



    // 이미지 단일업로드 or 서브 이미지 업로드
    @PostMapping("/image")
    public int insertImage(@RequestParam("image")MultipartFile multipartFile, ImagesDto imagesDto) throws IOException {
        return insertImageService.insertImage(multipartFile, imagesDto);
    }

    // 사진 다중업로드
    @PostMapping("/images")
    public int insertImages(@RequestParam("image") List<MultipartFile> multipartFileList, ImagesDto imagesDto){
        return insertImageService.insertImages(multipartFileList, imagesDto);
    }

    // 메인 이미지 업로드
    @PostMapping("/main-image")
    public int insertMainImage(@RequestParam("image")MultipartFile multipartFile, ImagesDto imagesDto){
        return insertImageService.insertMainImage(multipartFile, imagesDto);
    }

    // 해당 글의 모든 사진데이터 불러오기
    @GetMapping(value="/image" , consumes = "text/html")
    public Result getImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getImageInfo(bNo, serviceName);
    }

    // 해당 글의 메인사진 데이터 불러오기
    @GetMapping(value="/main-image", consumes = "text/html")
    public ImagesDto getMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getMainImage(bNo, serviceName);
    }

    // 해당 글의 서브사진 데이터 불러오기
    @GetMapping(value="/sub-image",  consumes = "text/html")
    public Result getSubImages(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getSubImages(bNo, serviceName);
    }

    // 수정페이지에서 메인사진 변경하기
    @PatchMapping(value="/main-image", consumes = "text/html")
    public int changeMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName, @RequestParam("imageName")String imageName){
        return updateImageService.changeMainImage(bNo, serviceName, imageName);
    }

    // 해당 글사진 삭제
    @DeleteMapping(value= "/image", consumes = "text/html")
    public int deleteImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return deleteImageService.deleteImage(bNo, serviceName);
    }

    // 단일 파일 삭제
    @DeleteMapping(value = "/image-detail", consumes = "text/html")
    public int deleteImageDetail(@RequestParam("imageName")String imageName){
        return deleteImageService.deleteImageDetail(imageName);
    }
}
