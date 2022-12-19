package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.File;
import lombok.*;

@Data
@NoArgsConstructor
public class FileDto {

    private Long id;
    private Application application;
    private String uploadFileName;
    private String storeFileName;
    private String url;


    public FileDto(File file) {
        this.id = file.getId();
        this.application = file.getApplication();
        this.uploadFileName = file.getUploadFileName();
        this.storeFileName = file.getStoreFileName();
        this.url = file.getUrl();
    }

    public File toEntity(){
        File file = File.builder()
                .id(id)
                .application(application)
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .url(url)
                .build();
        return file;
    }

    @Builder
    public FileDto(Long id, Application application, String uploadFileName, String storeFileName, String url) {
        this.id = id;
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }
}
