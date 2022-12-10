package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private Long id;
    private Application application;
    private String uploadFileName;
    private String storeFileName;

}
