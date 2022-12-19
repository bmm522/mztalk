package com.mztalk.resource.domain.request.dto;

import com.mztalk.resource.domain.entity.Images;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagesRequestDto {

    @ApiParam(value = "해당 글 번호", required = true, example = "1", type = "String")
    @ApiModelProperty(notes = "해당 글 번호", example = "1")
    private long bNo;

    @ApiParam(value = "서비스 이름", required = true, example = "testService", type = "String")
    @ApiModelProperty(notes = "서비스 이름", example = "testServer")
    private String serviceName;

    public Images toImagesWhenMain(String name, ConcurrentHashMap<String, String> s3Map){
        return Images.builder()
                .objectKey(s3Map.get("key"))
                .imageName(name)
                .imageUrl(s3Map.get("url"))
                .serviceName(serviceName)
                .bNo(bNo)
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
                .bNo(bNo)
                .imageLevel(1)
                .status("Y")
                .build();
    }
}
