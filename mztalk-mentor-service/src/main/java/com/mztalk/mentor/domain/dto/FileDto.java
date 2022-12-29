package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.File;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FileDto {

    private Long id;
    private String fileName;
    private String objectKey;
    private String fileUrl;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;


    public FileDto(File file) {
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.objectKey = file.getObjectKey();
        this.fileUrl = file.getFileUrl();
        this.createdDate = file.getCreatedDate();
        this.lastModifiedDate = file.getLastModifiedDate();
    }

    public File toEntity(){
        File file = File.builder()
                .id(id)
                .fileName(fileName)
                .objectKey(objectKey)
                .fileUrl(fileUrl)
                .build();
        return file;
    }

    @Builder
    public FileDto(Long id, String fileName, String objectKey, String fileUrl, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.fileName = fileName;
        this.objectKey = objectKey;
        this.fileUrl = fileUrl;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
