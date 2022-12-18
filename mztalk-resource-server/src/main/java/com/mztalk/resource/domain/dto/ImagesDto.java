package com.mztalk.resource.domain.dto;

import com.mztalk.resource.domain.entity.Images;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDto {



    @ApiParam(value = "이미지 번호", required = true, example = "1", type = "long")
    @ApiModelProperty(notes = "이미지 번호", example = "1")
//    @ApiModelProperty(notes = "이미지 번호", example = "1")
    private String imageNo;

    @ApiParam(value = "이미지 이름", required = true, example = "00a16943-bfcc-4184-acae-fa09e테스트이미지.jpg", type = "String")
    @ApiModelProperty(notes = "이미지 이름", example = "00a16943-bfcc-4184-acae-fa09e테스트이미지.jpg")
    private String imageName;


    @ApiParam(value = "이미지 경로", required = true, example = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/00a16943-bfcc-4184-acae-fa09e테스트이미지.jpg", type = "String")
    @ApiModelProperty(notes = "이미지 경로",example = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/00a16943-bfcc-4184-acae-fa09e테스트이미지.jpg")
    private String imageUrl;

    @ApiParam(value = "서비스 이름", required = true, example = "testService", type = "String")
    @ApiModelProperty(notes = "서비스 이름", example = "testServer")
    private String serviceName;

    @ApiParam(value = "해당 글 번호", required = true, example = "1", type = "String")
    @ApiModelProperty(notes = "해당 글 번호", example = "1")
    private String bNo;

    @ApiParam(value="이미지 레벨", required = true, example = "0", type = "String")
    @ApiModelProperty(notes = "이미지 레벨", example = "1")
    private String imageLevel;


    public Images toImagesWhenMain(String name, ConcurrentHashMap<String, String> s3Map){
        return Images.builder()
                .objectKey(s3Map.get("key"))
                .imageName(name)
                .imageUrl(s3Map.get("url"))
                .serviceName(serviceName)
                .bNo(Long.parseLong(bNo))
                .imageLevel(0)
                .status("Y")
                .build();
    }

    public Images toImagesWhenSub(String name,ConcurrentHashMap<String, String> s3Map){
        return Images.builder()
                .objectKey(s3Map.get("key"))
                .imageName(name)
                .imageUrl(s3Map.get("url"))
                .serviceName(serviceName)
                .bNo(Long.parseLong(bNo))
                .imageLevel(1)
                .status("Y")
                .build();
    }

    public ImagesDto(Images i){
        this.imageNo = String.valueOf(i.getImageId());
        this.imageName=i.getImageName();
        this.imageUrl=i.getImageUrl();
        this.serviceName = i.getServiceName();
        this.bNo = String.valueOf(i.getBNo());
        this.imageLevel=String.valueOf(i.getImageLevel());
    }





}
