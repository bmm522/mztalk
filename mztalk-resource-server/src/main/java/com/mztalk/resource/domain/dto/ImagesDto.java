package com.mztalk.resource.domain.dto;

import com.mztalk.resource.domain.entity.Images;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDto {



    private String imageNo;
    private String imageUrl;
    private String serviceName;
    private String bNo;

    private String imageLevel;

    public Images toImages(String imagePath){
        return Images.builder()
                .imageUrl(imagePath)
                .serviceName(serviceName)
                .bNo(Long.parseLong(bNo))
                .imageLevel(Long.parseLong(imageLevel))
                .status("Y")
                .build();
    }

    public ImagesDto(Images i){
        this.imageNo = String.valueOf(i.getImageId());
        this.imageUrl=i.getImageUrl();
        this.serviceName = i.getServiceName();
        this.bNo = String.valueOf(i.getBNo());
        this.imageLevel=String.valueOf(i.getImageLevel());
    }
}
