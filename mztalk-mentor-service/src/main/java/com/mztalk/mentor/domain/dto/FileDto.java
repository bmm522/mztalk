package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.File;
import lombok.*;

@Data
@NoArgsConstructor
public class FileDto {

    private Long id;
    private Application application;
    private String fileName;
    private String objectKey;
    private String fileUrl;


    public FileDto(File file) {
        this.id = file.getId();
        this.application = file.getApplication();
        this.fileName = file.getFileName();
        this.objectKey = file.getObjectKey();
        this.fileUrl = file.getFileUrl();
    }

    public File toEntity(){
        File file = File.builder()
                .id(id)
                .application(application)
                .fileName(fileName)
                .objectKey(objectKey)
                .fileUrl(fileUrl)
                .build();
        return file;
    }

    @Builder
    public FileDto(Long id, Application application, String fileName, String objectKey, String fileUrl) {
        this.id = id;
        this.application = application;
        this.fileName = fileName;
        this.objectKey = objectKey;
        this.fileUrl = fileUrl;
    }
}
