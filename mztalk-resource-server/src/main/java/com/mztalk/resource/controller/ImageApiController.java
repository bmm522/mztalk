package com.mztalk.resource.controller;


import com.mztalk.resource.domain.request.dto.ImagesRequestDto;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.service.DeleteImageService;
import com.mztalk.resource.service.InsertImageService;
import com.mztalk.resource.service.SelectImageService;
import com.mztalk.resource.service.UpdateImageService;
import com.mztalk.resource.service.impl.InsertImageServiceImpl;
import io.swagger.annotations.*;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/resource")
@RequiredArgsConstructor
@Api(tags = "image-api")
@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "bad request"),
        @ApiResponse(code = 500, message = "server error")
})
public class ImageApiController {

    private final InsertImageService insertImageService;

    private final SelectImageService selectImageService;

    private final UpdateImageService updateImageService;

    private final DeleteImageService deleteImageService;




    // 이미지 단일업로드 or 서브 이미지 업로드 or 기존 사진에 서브 이미지 업로드
    @ApiOperation(value="이미지 단일 업로드", notes = "이미지를 단일로 보냅니다. 사진레벨은 1로 지정됩니다.", response = ResponseData.class)
    @PostMapping(value= "/image", consumes = "multipart/form-data",  produces = "application/json")
    public ResponseEntity<?> insertImage(@RequestParam("image")MultipartFile multipartFile, ImagesRequestDto imagesRequestDto) throws IOException {
        return insertImageService.insertImage(multipartFile, imagesRequestDto);
    }

    // 사진 다중업로드
    @ApiOperation(value="이미지 다중 업로드", notes = "이미지를 여러장 보냅니다. 맨 첫번째 사진은 자동으로 레벨이 0으로 지정됩니다.", response = ResponseData.class)
    @PostMapping(value = "/images",  consumes = "multipart/form-data",  produces = "application/json")
    public ResponseEntity<?> insertImages(@RequestParam("image") List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto){
        return insertImageService.insertImages(multipartFileList, imagesRequestDto);
    }

    // 메인 이미지 업로드
    @ApiOperation(value="메인사진 업로드", notes = "메인이미지로 설정하고자 하는 곳에 요청을 보내면 자동으로 메인사진으로 등록됩니다.", consumes = "text/html", response = ResponseData.class)
 //           @ApiImplicitParam(name="image", value="이미지", dataType="MultipartFile", paramType = "param")
//            @ApiImplicitParam(name="bNo", value="글번호", dataType = "String", paramType = "param")

    @PostMapping(value = "/main-image",  consumes = "multipart/form-data",  produces = "application/json")
    public ResponseEntity<?> insertMainImage(@RequestParam("image")MultipartFile multipartFile, ImagesRequestDto imagesRequestDto){
        return insertImageService.insertMainImage(multipartFile, imagesRequestDto);
    }


    @ApiOperation(value="수정페이지에서의 업로드", notes = "수정페이지에서 업로드 할 때 요청을 보내면 됩니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParam(name="image", value="이미지", dataType="MultipartFile", paramType = "param")
    @PostMapping(value="/update-image", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<?> updateImage(@RequestParam("image")List<MultipartFile> multipartFileList, ImagesRequestDto imagesRequestDto){
        return insertImageService.updateImage(multipartFileList, imagesRequestDto);
    }


    // 해당 글의 모든 사진데이터 불러오기
    @ApiOperation(value="해당 글 이미지 정보 조회", notes = "해당 서비스의 글번호에 해당하는 모든 이미지를 리스트로 가져옵니다.", consumes = "text/html", produces = "application/json", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="bNo", value="해당 글 번호"),
            @ApiImplicitParam(name="serviceName", value="해당 서비스 이름")
    })
    @GetMapping(value="/images" , consumes = "text/html", produces = "application/json")
    public ResponseEntity<?> getImages(@RequestParam("bNo")String bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getImageInfo(Long.parseLong(bNo), serviceName);
    }

    // 해당 글의 메인사진만 데이터 불러오기
    @ApiOperation(value="메인 이미지 정보 조회", notes = "해당 서비스의 글번호에 해당하는 메인이미지를 가져옵니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="bNo", value="해당 글 번호"),
            @ApiImplicitParam(name="serviceName", value="해당 서비스 이름")
    })
    @GetMapping(value="/main-image", consumes = "text/html",  produces = "application/json")
    public ResponseEntity<?> getMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getMainImage(bNo, serviceName);
    }

    // 해당 글의 서브사진만 데이터 불러오기
    @ApiOperation(value="서브 이미지 정보 조회", notes = "해당 서비스의 글번호에 해당하는 서브이미지를 가져옵니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="bNo", value="해당 글 번호"),
            @ApiImplicitParam(name="serviceName", value="해당 서비스 이름")
    })
    @GetMapping(value="/sub-image",  consumes = "text/html", produces = "application/json")
    public ResponseEntity<?> getSubImages(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return selectImageService.getSubImages(bNo, serviceName);
    }


     // 수정페이지에서 메인사진 변경하기
     // 여기서 imageName은 메인으로 등록하고자 하는 파일의 이름.

    @ApiOperation(value="메인 이미지 수정", notes = "해당 서비스의 글번호에 해당하는 서브이미지를 메인이미지로 변경합니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="bNo", value="해당 글 번호"),
            @ApiImplicitParam(name="serviceName", value="해당 서비스 이름"),
            @ApiImplicitParam(name="imageName", value="objectKey를 입력해주세요")
    })
    @PatchMapping(value="/main-image", consumes = "text/html")
    public ResponseEntity<?> changeMainImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName, @RequestParam("imageName")String objectKey){
        return updateImageService.changeMainImage(bNo, serviceName, objectKey);
    }




    // 해당 글사진 삭제
    @ApiOperation(value="게시글 사진 삭제", notes = "해당 서비스의 글번호에 해당하는 모든 이미지를 삭제합니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="bNo", value="해당 글 번호"),
            @ApiImplicitParam(name="serviceName", value="해당 서비스 이름")
    })
    @DeleteMapping(value= "/images", consumes = "text/html")
    public ResponseEntity<?> deleteImage(@RequestParam("bNo")long bNo, @RequestParam("serviceName")String serviceName){
        return deleteImageService.deleteImage(bNo, serviceName);
    }

    // 단일 파일 삭제
    @ApiOperation(value="특정 사진 삭제", notes = "특정 이미지를 삭제합니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="imageName", value="objectKey를 입력해주세요")
    })
    @DeleteMapping(value = "/image-detail", consumes = "text/html")
    public ResponseEntity<?> deleteImageDetail(@RequestParam("imageName")String objectKey){
        return deleteImageService.deleteImageDetail(objectKey);
    }

}

// 각 DB에 필요한