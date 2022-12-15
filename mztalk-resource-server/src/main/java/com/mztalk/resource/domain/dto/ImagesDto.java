package com.mztalk.resource.domain.dto;

import com.mztalk.resource.domain.entity.Images;
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



    private String imageNo;
    private String imageName;
    private String imageUrl;
    private String serviceName;
    private String bNo;
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
