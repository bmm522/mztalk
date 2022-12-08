package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import lombok.*;

@Data
public class FileDto {

    private Long id;
    private Application application;
    private String uploadFileName;
    private String storeFileName;
    private String extension;

}
