package com.mztalk.resource.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImagesDto {



    private String serviceName;
    private String bNo;

    private String imageLevel;
}
